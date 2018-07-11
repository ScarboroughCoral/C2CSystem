package top.scarboroughcoral.c2c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.scarboroughcoral.c2c.model.dto.*;
import top.scarboroughcoral.c2c.model.entity.HouseType;
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

    @PostMapping("/changeHouseState")
    public BaseResult<Integer> changeHouseState(@RequestParam("houseId") Integer houseId,
                                                @RequestParam("houseStateId") Integer houseStateId){

        BaseResult<Integer> result = new BaseResult<>();
        houseService.changeHouseState(houseId,houseStateId,result);
        return result;
    }


    @PostMapping("/changeHouseInfo")
    public BaseResult<Object> changeHouseInfo(@RequestParam("houseId") Integer houseId,
                                              @RequestBody HouseDescDTO houseDescDTO){
        BaseResult<Object> result = new BaseResult<>();
        houseService.changeHouseInfo(houseId,houseDescDTO,result);
        return result;
    }

    @GetMapping("/getRentHouseInfo")
    public BaseResult<RentHouseMsgDTO> getRentHouseInfo(@RequestParam("houseId") Integer houseId){
        BaseResult<RentHouseMsgDTO> result = new BaseResult<>();
        houseService.getRentHouseInfo(houseId,result);
        return result;
    }

}
