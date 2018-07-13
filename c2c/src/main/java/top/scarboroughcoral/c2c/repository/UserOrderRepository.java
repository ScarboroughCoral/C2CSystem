package top.scarboroughcoral.c2c.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import top.scarboroughcoral.c2c.model.dto.OrderDTO;
import top.scarboroughcoral.c2c.model.entity.UserOrder;

import java.util.List;

public interface UserOrderRepository extends JpaRepository<UserOrder,Integer> {

    @Query(value = "select new top.scarboroughcoral.c2c.model.dto.OrderDTO(uo.orderId,u.name,os.orderStatus,ht.houseType,h.price,h.houseArea," +
            "h.houseAddr,h.houseDesc,p.provinceDesc,c.cityDesc,d.districtDesc,uo.orderTime,h.houseId) " +
            "from House h,HouseType ht,User u,OrderStatus os,UserOrder uo,Province p,City c,District d " +
            "where h.houseId=uo.houseID and h.houseTypeId=ht.houseTypeId and uo.orderStatusId=os.orderStatusId and u.userId=uo.userId " +
            "and h.districtId=d.districtId and d.cityId=c.cityId and p.provinceId=c.provinceId and u.userId=?1 and uo.orderStatusId=2")
    List<OrderDTO> getOrderMsg(Integer userId);


    @Query(value = "update house h,userorder uo set h.house_status_id=1,uo.order_status_id=4 " +
            "where uo.order_id=?1 and uo.house_id=h.house_id",nativeQuery = true)
    @Modifying
    Integer checkout(Integer orderId);
}
