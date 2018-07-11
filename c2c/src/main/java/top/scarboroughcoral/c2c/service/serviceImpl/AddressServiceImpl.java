package top.scarboroughcoral.c2c.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.scarboroughcoral.c2c.model.dto.AddressDTO;
import top.scarboroughcoral.c2c.model.dto.HouseMsgDTO;
import top.scarboroughcoral.c2c.model.entity.City;
import top.scarboroughcoral.c2c.model.entity.District;
import top.scarboroughcoral.c2c.model.entity.Province;
import top.scarboroughcoral.c2c.model.result.BaseResult;
import top.scarboroughcoral.c2c.repository.AddressRepository;
import top.scarboroughcoral.c2c.repository.CityRepository;
import top.scarboroughcoral.c2c.repository.HouseRepository;
import top.scarboroughcoral.c2c.repository.ProvinceRepository;
import top.scarboroughcoral.c2c.service.AddressService;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private HouseRepository houseRepository;

    @Override
    public void getAddress(Integer districtId, BaseResult<AddressDTO> result) {
        AddressDTO addressDTO = addressRepository.getAddress(districtId);
        if(addressDTO != null){
            result.setMessage("获取地址成功");
            result.setSuccess(true);
            result.setData(addressDTO);
        }else{
            result.setMessage("地址不存在");
            result.setSuccess(false);
        }
    }

    @Override
    public void getHouseAddress(Integer houseId, BaseResult<AddressDTO> result) {
        AddressDTO addressDTO = addressRepository.getHouseAddress(houseId);
        if(addressDTO != null){
            result.setMessage("获取地址成功");
            result.setSuccess(true);
            result.setData(addressDTO);
        }else{
            result.setMessage("房子不存在");
            result.setSuccess(false);
        }
    }

    @Override
    public void getCity(BaseResult<List<City>> result) {
        List<City> cityList = cityRepository.getCity();
        if(cityList != null){
            result.setSuccess(true);
            result.setMessage("获取成功");
            result.setData(cityList);
        }else{
            result.setMessage("失败");
            result.setSuccess(false);
        }
    }

    @Override
    public void getProvince(BaseResult<List<Province>> result) {
        List<Province> provinceList = provinceRepository.getProvince();
        if(provinceList != null){
            result.setSuccess(true);
            result.setMessage("获取成功");
            result.setData(provinceList);
        }else{
            result.setMessage("失败");
            result.setSuccess(false);
        }
    }

    @Override
    public void getDistrict(BaseResult<List<District>> result) {
        List<District> districtList = addressRepository.getDistrict();
        if(districtList != null){
            result.setSuccess(true);
            result.setMessage("获取成功");
            result.setData(districtList);
        }else{
            result.setMessage("失败");
            result.setSuccess(false);
        }
    }

    @Override
    public void getCityByProvince(Integer provinceId, BaseResult<List<City>> result) {
        List<City> cityList = cityRepository.getCityByProvince(provinceId);
        if(cityList != null){
            result.setSuccess(true);
            result.setData(cityList);
        }else{
            result.setSuccess(false);
            result.setMessage("没有登记该城市");
        }
    }

    @Override
    public void getDistrictByCity(Integer cityId, BaseResult<List<District>> result) {
        List<District> districtList = addressRepository.getDistrictByCity(cityId);
        if(districtList != null){
            result.setSuccess(true);
            result.setData(districtList);
        }else{
            result.setSuccess(false);
            result.setMessage("没有登记该区域");
        }
    }

    @Override
    public void searchByProvince(Integer provinceId, BaseResult<List<HouseMsgDTO>> result) {
        List<HouseMsgDTO> houseMsgDTOList = addressRepository.searchHouseByProvince(provinceId);
        setHouseMsgResult(result,houseMsgDTOList);
    }

    @Override
    public void searchByCity(Integer cityId, BaseResult<List<HouseMsgDTO>> result) {
        List<HouseMsgDTO> houseMsgDTOList = addressRepository.searchHouseByCity(cityId);
        setHouseMsgResult(result,houseMsgDTOList);
    }

    @Override
    public void searchByDistrict(Integer districtId, BaseResult<List<HouseMsgDTO>> result) {
        List<HouseMsgDTO> houseMsgDTOList = addressRepository.searchHouseByDistrict(districtId);
        setHouseMsgResult(result,houseMsgDTOList);
    }

    @Override
    public void search(Integer provinceId, Integer cityId, Integer districtId, Integer houseTypeId,BaseResult<List<HouseMsgDTO>> result) {
        List<HouseMsgDTO> houseMsgDTOList = null;
        if(houseTypeId == null && provinceId != null){
            if(cityId == null){
                houseMsgDTOList = addressRepository.searchHouseByProvince(provinceId);
            }
            else if (districtId == null){
                houseMsgDTOList = addressRepository.searchHouseByCity(cityId);
            }
            else {
                houseMsgDTOList = addressRepository.searchHouseByDistrict(districtId);
            }
        }else if (houseTypeId !=  null){
            if(provinceId == null){
                houseMsgDTOList = houseRepository.getHouseMsgByType(houseTypeId);
            }
            else  if(cityId == null){
                houseMsgDTOList = addressRepository.searchHouseByProvinceAndType(provinceId,houseTypeId);
            }
            else if (districtId == null){
                houseMsgDTOList = addressRepository.searchHouseByCityAndType(cityId,houseTypeId);
            }
            else {
                houseMsgDTOList = addressRepository.searchHouseByDistrictAndType(districtId,houseTypeId);
            }

        }else{
            houseMsgDTOList = houseRepository.getHouseMsg();
        }

        if(houseMsgDTOList != null){
            result.setMessage("搜索成功");
            result.setSuccess(true);
            result.setData(houseMsgDTOList);
        }
        else{
            result.setMessage("信息不存在");
            result.setSuccess(false);
        }

    }

    private void setHouseMsgResult(BaseResult<List<HouseMsgDTO>> result,List<HouseMsgDTO> houseMsgDTOList){
        if(houseMsgDTOList!=null){
            result.setMessage("获取成功");
            result.setSuccess(true);
            result.setData(houseMsgDTOList);
        }else{
            result.setMessage("获取失败");
            result.setSuccess(false);
        }
    }

}
