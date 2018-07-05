package top.scarboroughcoral.c2c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.scarboroughcoral.c2c.model.dto.HouseMsgDTO;
import top.scarboroughcoral.c2c.model.result.BaseResult;
import top.scarboroughcoral.c2c.service.HouseService;

@RestController
@RequestMapping("/lobby")
public class HouseController {

    @Autowired
    private HouseService houseService;


    @GetMapping("/getHouseInfo")
    public BaseResult<HouseMsgDTO> getHouseInfo(){
        BaseResult<HouseMsgDTO> result = new BaseResult<>();
        houseService.getHouseMsg(result);
        return result;
    }

}
