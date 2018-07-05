package top.scarboroughcoral.c2c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.scarboroughcoral.c2c.model.dto.HouseMsgDTO;
import top.scarboroughcoral.c2c.model.result.BaseResult;
import top.scarboroughcoral.c2c.service.HouseService;

import java.util.List;

@RestController
@RequestMapping(value = "/lobby")
public class HouseController {

    @Autowired
    private HouseService houseService;


    @GetMapping("/getHouseInfo")
    public BaseResult<List<HouseMsgDTO>> getHouseInfo(){
        BaseResult<List<HouseMsgDTO>> result = new BaseResult<>();
        houseService.getHouseMsg(result);
        return result;
    }

}
