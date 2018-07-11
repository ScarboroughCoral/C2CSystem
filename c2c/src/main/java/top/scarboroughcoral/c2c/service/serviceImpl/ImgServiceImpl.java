package top.scarboroughcoral.c2c.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.scarboroughcoral.c2c.model.entity.HouseImg;
import top.scarboroughcoral.c2c.model.result.BaseResult;
import top.scarboroughcoral.c2c.repository.ImgRepository;
import top.scarboroughcoral.c2c.service.ImgService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ImgServiceImpl implements ImgService {

    @Autowired
    private ImgRepository imgRepository;

    @Value(value = "${ResourcePath}")
    private String recoursePath;

    @Value(value = "${getResourcePath}")
    private String urlPath;


    @Override
    public void setImg(Integer houseId, MultipartFile img, BaseResult<Object> result) {
        String targetPath = recoursePath + houseId + "\\";
        String url = urlPath + houseId +"/";
        try {
            byte[] imgBytes = img.getBytes();
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String fileName = dateFormat.format(System.currentTimeMillis()) + "_" +img.getOriginalFilename();
            File targetFile = new File(targetPath);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            Path path = Paths.get(targetPath,fileName);
            Files.write(path,imgBytes);

            url += fileName;
            HouseImg houseImg = new HouseImg(url,houseId);
            imgRepository.save(houseImg);

            result.setMessage("上传成功");
            result.setSuccess(true);

        } catch (IOException e) {
            e.printStackTrace();

            result.setMessage("上传失败");
            result.setSuccess(false);
        }
    }

    @Override
    public void getImgUrl(Integer houseId, BaseResult<List<String>> result) {
        List<String> stringList = imgRepository.getImgUrl(houseId);
        if(stringList != null){
            result.setMessage("获取成功");
            result.setSuccess(true);
            result.setData(stringList);
        }else{
            result.setSuccess(false);
            result.setMessage("没有相应的图片信息");
        }
    }
}
