package top.scarboroughcoral.c2c.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.scarboroughcoral.c2c.model.entity.HouseImg;
import top.scarboroughcoral.c2c.model.result.BaseResult;
import top.scarboroughcoral.c2c.repository.ImgRepository;
import top.scarboroughcoral.c2c.service.ImgService;

import java.util.List;

@Service
public class ImgServiceImpl implements ImgService {

    @Autowired
    private ImgRepository imgRepository;


    @Override
    public void setImgUrl(Integer houseId, String imgUrl, BaseResult<Object> result) {
        HouseImg houseImg = new HouseImg(imgUrl,houseId);
        HouseImg imgMsg = imgRepository.save(houseImg);
        if(houseImg != null){
            result.setMessage("保存成功");
            result.setSuccess(true);
        }else{
            result.setMessage("保存失败");
            result.setSuccess(false);
        }
    }

    @Override
    public void setImgList(Integer houseId, List<String> imgUrlList, BaseResult<Object> result) {
        for (String url: imgUrlList) {
            HouseImg houseImg = new HouseImg(url,houseId);
            imgRepository.save(houseImg);
        }

        result.setMessage("保存成功");
        result.setSuccess(true);
    }

    @Override
    public void getImgUrl(Integer houseId, BaseResult<List<String>> result) {

    }
}
