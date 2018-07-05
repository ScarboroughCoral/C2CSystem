package top.scarboroughcoral.c2c.service;

import top.scarboroughcoral.c2c.model.dto.HouseMsgDTO;
import top.scarboroughcoral.c2c.model.result.BaseResult;

public interface HouseService {

    void getHouseMsg(BaseResult<HouseMsgDTO> result);
}
