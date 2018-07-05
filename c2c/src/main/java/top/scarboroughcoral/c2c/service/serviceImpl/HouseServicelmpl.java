package top.scarboroughcoral.c2c.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.scarboroughcoral.c2c.model.dto.HouseMsgDTO;
import top.scarboroughcoral.c2c.model.dto.RentDTO;
import top.scarboroughcoral.c2c.model.entity.House;
import top.scarboroughcoral.c2c.model.entity.HouseType;
import top.scarboroughcoral.c2c.model.entity.HousesOfRenters;
import top.scarboroughcoral.c2c.model.result.BaseResult;
import top.scarboroughcoral.c2c.repository.HouseRepository;
import top.scarboroughcoral.c2c.repository.House_Of_RenterRepository;
import top.scarboroughcoral.c2c.service.HouseService;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class HouseServicelmpl implements HouseService {

    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private House_Of_RenterRepository hrRepository;

    @Override
    public void getHouseMsg(BaseResult<List<HouseMsgDTO>> result) {
        List<HouseMsgDTO> houseMsgList = houseRepository.getHouseMsg();
        if (houseMsgList != null) {
            result.setMessage("获取消息成功");
            result.setSuccess(true);
            result.setData(houseMsgList);
        } else {
            result.setMessage("获取信息失败");
            result.setSuccess(false);
        }
    }

    @Override
    public void rentHouse(RentDTO rentDTO, BaseResult<Object> result) {
        House house = new House();
        house.setHouseAddr(rentDTO.getHouseAddr());
        house.setHouseDesc(rentDTO.getHouseDecs());
        house.setHouseArea(rentDTO.getHouseArea());
        house.setPrice(rentDTO.getPrice());
        house.setHouseStatusId(rentDTO.getHouseStateID());
        house.setHouseTypeId(rentDTO.getHouseTypeID());
        house.setHoldNumber(rentDTO.getHoldNum());

        House h = houseRepository.save(house);
        HousesOfRenters housesOfRenters = new HousesOfRenters(rentDTO.getUserId(),h.getHouseId());
        HousesOfRenters res =  hrRepository.save(housesOfRenters);

        if(res.getHouseId() == null){
            result.setMessage("发布失败");
            result.setSuccess(false);
        }
        else{
            result.setMessage("发布成功");
            result.setSuccess(true);
        }
    }

    @Override
    public void getHouseType(BaseResult<List<HouseType>> result) {
        List<HouseType> houseTypeList = houseRepository.getHouseType();
        if(houseTypeList == null){
            result.setMessage("网断啦shazi");
            result.setSuccess(false);
        }else{
            result.setSuccess(true);
            result.setData(houseTypeList);
        }
    }

    @Override
    public void getRenterHouseMsg(Integer userID, BaseResult<List<HouseMsgDTO>> result) {
        List<HouseMsgDTO> hl = houseRepository.getRenterHouseMsg(userID);
        if(hl != null){
            result.setSuccess(true);
            result.setMessage("哇啊啊啊啊成成成功啦!!!!!!!!!!!");
            result.setData(hl);
        }else{
            result.setSuccess(false);
            result.setMessage("你要的东西根本就没有好吧");
        }
    }


}
