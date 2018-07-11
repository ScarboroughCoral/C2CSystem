package top.scarboroughcoral.c2c.service;

import top.scarboroughcoral.c2c.model.dto.AddressDTO;
import top.scarboroughcoral.c2c.model.dto.HouseMsgDTO;
import top.scarboroughcoral.c2c.model.entity.City;
import top.scarboroughcoral.c2c.model.entity.District;
import top.scarboroughcoral.c2c.model.entity.Province;
import top.scarboroughcoral.c2c.model.result.BaseResult;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface AddressService {

    void getAddress(Integer districtId, BaseResult<AddressDTO> result);
    void getHouseAddress(Integer houseId, BaseResult<AddressDTO> result);

    void getCity(BaseResult<List<City>> result);
    void getProvince(BaseResult<List<Province>> result);
    void getDistrict(BaseResult<List<District>> result);

    void getCityByProvince(Integer provinceId,BaseResult<List<City>> result);
    void getDistrictByCity(Integer cityId,BaseResult<List<District>> result);

    void searchByProvince(Integer provinceId,BaseResult<List<HouseMsgDTO>> result);
    void searchByCity(Integer cityId,BaseResult<List<HouseMsgDTO>>result);
    void searchByDistrict(Integer districtId,BaseResult<List<HouseMsgDTO>>result);

    void search(Integer provinceId,Integer cityId,Integer districtId,Integer houseType,BaseResult<List<HouseMsgDTO>> result);

}
