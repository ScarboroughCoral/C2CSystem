package top.scarboroughcoral.c2c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.scarboroughcoral.c2c.model.result.BaseResult;
import top.scarboroughcoral.c2c.service.ImgService;

import java.util.List;

@RestController
@RequestMapping(value = "/img")
public class ImgController {

    @Autowired
    private ImgService imgService;


    @PostMapping("/setHouseImg")
    public BaseResult<Object> setImgList(@RequestParam(value = "houseId") Integer houseId,
                                         @RequestParam(value = "img") MultipartFile img ){
        BaseResult<Object> result = new BaseResult<>();
        imgService.setImg(houseId,img,result);
        return result;
    }

    @GetMapping("/getHouseImg")
    public BaseResult<List<String>> getImg(@RequestParam(value = "houseId") Integer houseId){
        BaseResult<List<String>> result=new BaseResult<>();
        imgService.getImgUrl(houseId,result);
        return result;
    }

}
