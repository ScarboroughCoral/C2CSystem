package top.scarboroughcoral.c2c.service;

import top.scarboroughcoral.c2c.model.dto.HouseDescDTO;
import top.scarboroughcoral.c2c.model.dto.HouseMsgDTO;
import top.scarboroughcoral.c2c.model.dto.RentDTO;
import top.scarboroughcoral.c2c.model.dto.RenterHouseMsgDTO;
import top.scarboroughcoral.c2c.model.entity.HouseType;
import top.scarboroughcoral.c2c.model.result.BaseResult;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface HouseService {

    void getHouseMsg(BaseResult<List<HouseMsgDTO>> result);
    void rentHouse(RentDTO rentDTO, BaseResult<Object> result);
    void getHouseType(BaseResult<List<HouseType>> result);
    void getRenterHouseMsg(Integer userID,BaseResult<List<RenterHouseMsgDTO>> result);
    void changeHouseState(Integer houseId,Integer houseStateId,BaseResult<Integer> result);
    void changeHouseInfo(Integer houseId, HouseDescDTO houseDescDTO,BaseResult<Object> result);

}
