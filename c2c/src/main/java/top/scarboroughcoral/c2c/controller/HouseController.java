package top.scarboroughcoral.c2c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.scarboroughcoral.c2c.model.dto.AddressDTO;
import top.scarboroughcoral.c2c.model.dto.HouseMsgDTO;
import top.scarboroughcoral.c2c.model.dto.RentDTO;
import top.scarboroughcoral.c2c.model.dto.RenterHouseMsgDTO;
import top.scarboroughcoral.c2c.model.entity.HouseType;
import top.scarboroughcoral.c2c.model.result.BaseResult;
import top.scarboroughcoral.c2c.service.AddressService;
import top.scarboroughcoral.c2c.service.HouseService;
import top.scarboroughcoral.c2c.util.ResultUtil;

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

    @PostMapping("/rentHouse")
    public BaseResult<Object> rentHouse(@RequestBody RentDTO rentDTO){
        System.out.println(rentDTO.getUserId());
        BaseResult<Object> result = new BaseResult<>();
        houseService.rentHouse(rentDTO,result);
        return result;
    }

    @PostMapping("/renterHouseMsg")
    public BaseResult<List<RenterHouseMsgDTO>> getRenterHouseMsg(@RequestParam("userID") int  userID){
        BaseResult<List<RenterHouseMsgDTO>> r = new BaseResult<>();
        houseService.getRenterHouseMsg(userID,r);
        return r;
    }

    @GetMapping("/getHouseType")
    public BaseResult<List<HouseType>> getHouseType(){
        BaseResult<List<HouseType>> r = new BaseResult<>();
        houseService.getHouseType(r);
        return r;
    }


}
