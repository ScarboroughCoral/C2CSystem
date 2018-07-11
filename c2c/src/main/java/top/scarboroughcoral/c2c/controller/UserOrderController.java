package top.scarboroughcoral.c2c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.scarboroughcoral.c2c.model.dto.OrderDTO;
import top.scarboroughcoral.c2c.model.result.BaseResult;
import top.scarboroughcoral.c2c.service.UserOrderService;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class UserOrderController {

    @Autowired
    private UserOrderService userOrderService;

    @PostMapping("/setOrder")
    public BaseResult<Object> setUserOder(@RequestParam("userId") Integer userId,
                                          @RequestParam("houseId") Integer houseId,
                                          @RequestParam(value = "orderStateId",required = false) Integer orderStateId){
        BaseResult<Object> result = new BaseResult<>();
        userOrderService.orderHouse(houseId,userId,orderStateId,result);
        return result;
    }

    @GetMapping("/getOrderMsg")
    public BaseResult<List<OrderDTO>> getOrderMsg(@RequestParam("userId") Integer userId){
        BaseResult<List<OrderDTO>> result = new BaseResult<>();
        userOrderService.getOrderMsg(userId,result);
        return result;
    }

}
