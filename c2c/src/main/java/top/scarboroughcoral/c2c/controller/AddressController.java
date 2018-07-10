package top.scarboroughcoral.c2c.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.scarboroughcoral.c2c.model.dto.AddressDTO;
import top.scarboroughcoral.c2c.model.dto.HouseMsgDTO;
import top.scarboroughcoral.c2c.model.entity.City;
import top.scarboroughcoral.c2c.model.entity.District;
import top.scarboroughcoral.c2c.model.entity.Province;
import top.scarboroughcoral.c2c.model.result.BaseResult;
import top.scarboroughcoral.c2c.service.AddressService;

import java.util.List;

@RestController
@RequestMapping(value = "/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/getAddress")
    public BaseResult<AddressDTO> getAddress(@RequestParam("districtId") Integer districtID){
        BaseResult<AddressDTO> result = new BaseResult<>();
        addressService.getAddress(districtID,result);
        return result;
    }

    @GetMapping("/getHouseAddress")
    public BaseResult<AddressDTO> getHouseAddress(@RequestParam("houseId") Integer districtID){
        BaseResult<AddressDTO> result = new BaseResult<>();
        addressService.getHouseAddress(districtID,result);
        return result;
    }

    @GetMapping("/province")
    public BaseResult<List<Province>> getProvince(){
        BaseResult<List<Province>> result = new BaseResult<>();
        addressService.getProvince(result);
        return result;
    }

    @GetMapping("/city")
    public BaseResult<List<City>> getCity(){
        BaseResult<List<City>> result = new BaseResult<>();
        addressService.getCity(result);
        return result;
    }

    @GetMapping("/district")
    public BaseResult<List<District>> getDistrict(){
        BaseResult<List<District>> result= new BaseResult<>();
        addressService.getDistrict(result);
        return result;
    }

    @GetMapping("/getByProvince")
    public BaseResult<List<HouseMsgDTO>> searchByProvince(@RequestParam("provinceId") Integer provinceId){
        BaseResult<List<HouseMsgDTO>> result = new BaseResult<>();
        addressService.searchByProvince(provinceId,result);
        return result;
    }

    @GetMapping("/getByCity")
    public BaseResult<List<HouseMsgDTO>> searchByCity(@RequestParam("cityId") Integer cityId){
        BaseResult<List<HouseMsgDTO>> result = new BaseResult<>();
        addressService.searchByCity(cityId,result);
        return result;
    }

    @GetMapping("/getByDistrict")
    public BaseResult<List<HouseMsgDTO>> searchByDistrict(@RequestParam("districtId") Integer districtId){
        BaseResult<List<HouseMsgDTO>> result = new BaseResult<>();
        addressService.searchByDistrict(districtId,result);
        return result;
    }

}
