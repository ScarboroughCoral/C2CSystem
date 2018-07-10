package top.scarboroughcoral.c2c.service;

import top.scarboroughcoral.c2c.model.result.BaseResult;

import java.util.List;

public interface ImgService {

    void setImgUrl(Integer houseId, String imgUrl, BaseResult<Object> result);
    void setImgList(Integer houseId,List<String> imgUrlList,BaseResult<Object> result);
    void getImgUrl(Integer houseId,BaseResult<List<String>> result);

}
