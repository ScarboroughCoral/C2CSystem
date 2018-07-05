package top.scarboroughcoral.c2c.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.scarboroughcoral.c2c.model.dto.HouseMsgDTO;
import top.scarboroughcoral.c2c.model.result.BaseResult;
import top.scarboroughcoral.c2c.repository.HouseRepository;
import top.scarboroughcoral.c2c.service.HouseService;

import java.util.List;


@Service
public class HouseServicelmpl implements HouseService {

    @Autowired
    private HouseRepository houseRepository;

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

}
