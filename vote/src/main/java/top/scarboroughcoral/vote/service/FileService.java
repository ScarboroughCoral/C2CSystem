package top.scarboroughcoral.vote.service;

import org.springframework.web.multipart.MultipartFile;
import top.scarboroughcoral.vote.model.entity.File;
import top.scarboroughcoral.vote.model.result.BaseResult;

import java.io.IOException;
import java.util.List;

public interface FileService {

    public void upload(MultipartFile file,Integer meeting_id, BaseResult<Object> result);
    public void zipFile(Integer meetingId,BaseResult<Object> result);
    public void download(Integer meeting_id,BaseResult<Object> result);
    public void getFolder(Integer meetingId, BaseResult<List<String>> result);
}
