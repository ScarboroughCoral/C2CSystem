package top.scarboroughcoral.c2c.service;

import top.scarboroughcoral.c2c.model.dto.OrderDTO;
import top.scarboroughcoral.c2c.model.result.BaseResult;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.ListJoin;
import java.util.List;

public interface UserOrderService {
    void orderHouse(Integer houseId, Integer userId,Integer orderStateId, BaseResult<Object> result);
    void getOrderMsg(Integer userId, BaseResult<List<OrderDTO>> result);
}
