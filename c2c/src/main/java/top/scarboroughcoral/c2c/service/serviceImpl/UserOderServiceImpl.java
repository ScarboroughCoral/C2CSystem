package top.scarboroughcoral.c2c.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.scarboroughcoral.c2c.model.dto.OrderDTO;
import top.scarboroughcoral.c2c.model.entity.UserOrder;
import top.scarboroughcoral.c2c.model.result.BaseResult;
import top.scarboroughcoral.c2c.repository.HouseRepository;
import top.scarboroughcoral.c2c.repository.UserOrderRepository;
import top.scarboroughcoral.c2c.service.UserOrderService;

import java.util.Date;
import java.util.List;

@Service
public class UserOderServiceImpl implements UserOrderService {

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private HouseRepository houseRepository;

    @Override
    @Transactional
    public void orderHouse(Integer houseId, Integer userId,Integer orderStateId,BaseResult<Object> result) {

        int state;

        if (orderStateId == null) {
            state = 2;
        } else {
            state = orderStateId;
        }

        UserOrder userOrder = new UserOrder(state, userId, new Date(), false, houseId);
        userOrderRepository.save(userOrder);
        houseRepository.changeHouseState(houseId,2);

        result.setSuccess(true);
        result.setMessage("订单建立成功");
    }

    @Override
    public void getOrderMsg(Integer userId, BaseResult<List<OrderDTO>> result) {
        List<OrderDTO> orderDTOList = userOrderRepository.getOrderMsg(userId);
        if(orderDTOList != null){
            result.setMessage("获取成功");
            result.setSuccess(true);
            result.setData(orderDTOList);
        }else{
            result.setMessage("failed");
            result.setSuccess(false);
        }
    }
}
