package top.scarboroughcoral.vote.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.scarboroughcoral.vote.model.entity.File;
import top.scarboroughcoral.vote.model.entity.Meeting;
import top.scarboroughcoral.vote.model.result.BaseResult;
import top.scarboroughcoral.vote.repository.FileRepository;
import top.scarboroughcoral.vote.repository.MeetingRepository;
import top.scarboroughcoral.vote.service.FileService;
import top.scarboroughcoral.vote.util.MD5;
import top.scarboroughcoral.vote.util.ZipUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ResourceUtils.getFile;
import static top.scarboroughcoral.vote.util.Constant.*;


@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private MeetingRepository meetingRepository;

    @Value(value = "${ResourcePath}")
    private String resourcePath;

    @Value("${getResourcePath}")
    private String getResourcePath;

    @Override
    public void upload(MultipartFile file,Integer meeting_id, BaseResult<Object> result) {

        String targetPath = resourcePath + meeting_id+"/";

        try {
            byte[] bytes = file.getBytes();
            String fileName = file.getOriginalFilename();
            java.io.File targetFile = new java.io.File(targetPath);
            if (!targetFile.exists()){
                targetFile.mkdirs();
            }
            Path path = Paths.get(targetPath+fileName);
            Files.write(path,bytes);
            result.setSuccess(true);
            result.setMessage("上传成功！");
        } catch (IOException e) {
            e.printStackTrace();
            result.setMessage("上传失败！未知原因");
        }
    }

    @Override
    public void zipFile(Integer meetingId, BaseResult<Object> result) {
        String sourcePath = resourcePath + meetingId;
        String targetPath = resourcePath + meetingId+".zip";
        String outPath = getResourcePath + meetingId+".zip";
        try {
            ZipUtil.compress(sourcePath , targetPath);
            String md5 =  MD5.md5sum(targetPath);
            File file = new File(meetingId,outPath,md5);
            fileRepository.save(file);
            result.setData(file);
            result.setSuccess(true);
            result.setMessage(SAVE_FILE_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            result.setMessage(SAVE_FILE_FAILURE);
        }
    }

    @Override
    public void download(Integer meeting_id, BaseResult<Object> result) {


            File file = fileRepository.findByMeeting_id(meeting_id);
            if (file==null){
                result.setMessage(FILE_NOT_FOUND);
                return;
            }
            result.setData(file);
            result.setSuccess(true);
            result.setMessage(GET_FILE_SUCCESS);

    }

    @Override
    public void getFolder(Integer meetingId, BaseResult<List<String>> result) {
        String sourcePath = resourcePath + meetingId;

        java.io.File file = new java.io.File(sourcePath);
        if (!file.exists()){
            file.mkdirs();
        }
        List<String> strings = new ArrayList<>();
        // get the folder list
        java.io.File[] array = file.listFiles();
        if (0==array.length){
            result.setMessage(FOLDER_EMPTY);
            return;
        }


        for(int i=0;i<array.length;i++){
            strings.add(array[i].getName());
        }
        result.setMessage(FOLDER_SUCCESS);
        result.setSuccess(true);
        result.setData(strings);

    }

}
