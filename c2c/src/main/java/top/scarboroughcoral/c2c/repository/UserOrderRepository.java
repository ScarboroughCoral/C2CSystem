package top.scarboroughcoral.c2c.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.scarboroughcoral.c2c.model.dto.OrderDTO;
import top.scarboroughcoral.c2c.model.entity.UserOrder;

import java.util.List;

public interface UserOrderRepository extends JpaRepository<UserOrder,Integer> {

    @Query(value = "select new top.scarboroughcoral.c2c.model.dto.OrderDTO(u.name,os.orderStatus,ht.houseType,h.price,h.houseArea," +
            "h.houseAddr,h.houseDesc,p.provinceDesc,c.cityDesc,d.districtDesc,uo.orderTime) " +
            "from House h,HouseType ht,User u,OrderStatus os,UserOrder uo,Province p,City c,District d " +
            "where h.houseId=uo.houseID and h.houseTypeId=ht.houseTypeId and uo.orderStatusId=os.orderStatusId and u.userId=uo.userId " +
            "and h.districtId=d.districtId and d.cityId=c.cityId and p.provinceId=c.provinceId and u.userId=?1")
    List<OrderDTO> getOrderMsg(Integer userId);
}
