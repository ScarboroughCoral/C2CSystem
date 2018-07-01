package top.scarboroughcoral.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.scarboroughcoral.vote.model.entity.File;
import top.scarboroughcoral.vote.model.result.BaseResult;
import top.scarboroughcoral.vote.service.FileService;

import java.util.List;


@RestController
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping("/upload")
    public BaseResult<Object> upload(@RequestParam("file") MultipartFile file,@RequestParam("meetingId") Integer meeting_id){

        BaseResult<Object> result = new BaseResult<>();
        fileService.upload(file,meeting_id,result);

        return result;
    }

    @RequestMapping("/zipFile")
    public BaseResult<Object> upload(@RequestParam("meetingId") Integer meeting_id){

        BaseResult<Object> result = new BaseResult<>();
        fileService.zipFile(meeting_id,result);

        return result;
    }

    @RequestMapping("/download")
    public BaseResult<Object> download(@RequestParam("meetingId") Integer meeting_id){
        BaseResult<Object> result = new BaseResult<>();
        fileService.download(meeting_id,result);
        return result;
    }

    @RequestMapping("/getFolder")
    public BaseResult<List<String>> getFolder(@RequestParam("meetingId") Integer meeting_id){
        BaseResult<List<String>> result = new BaseResult<>();
        fileService.getFolder(meeting_id,result);
        return result;
    }
}
