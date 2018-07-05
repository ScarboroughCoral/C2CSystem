package top.scarboroughcoral.c2c.service;

import top.scarboroughcoral.c2c.model.dto.HouseMsgDTO;
import top.scarboroughcoral.c2c.model.result.BaseResult;

import java.util.List;

public interface HouseService {

    void getHouseMsg(BaseResult<List<HouseMsgDTO>> result);
}
