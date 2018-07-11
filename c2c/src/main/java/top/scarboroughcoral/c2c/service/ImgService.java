package top.scarboroughcoral.c2c.service;

import org.springframework.web.multipart.MultipartFile;
import top.scarboroughcoral.c2c.model.result.BaseResult;

import java.io.File;
import java.util.List;

public interface ImgService {

    void setImg(Integer houseId, MultipartFile img, BaseResult<Object> result);
    void getImgUrl(Integer houseId,BaseResult<List<String>> result);

}
