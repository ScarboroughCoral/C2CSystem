package top.scarboroughcoral.c2c.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.scarboroughcoral.c2c.model.dto.HouseMsgDTO;
import top.scarboroughcoral.c2c.model.entity.House;
import top.scarboroughcoral.c2c.model.entity.HouseType;

import java.util.List;

public interface HouseRepository extends JpaRepository<House,Integer> {


    @Query(value = "select new top.scarboroughcoral.c2c.model.dto.HouseMsgDTO(ht.houseTypeDesc,h.houseDesc," +
            "h.houseAddr,u.phone,h.price,o.orderTime) "+
            "from User u,UserOrder o,HouseType ht,House h " +
            "where h.houseTypeId=ht.houseTypeId and h.orderId=o.orderId and o.userId=u.userId")
    List<HouseMsgDTO> getHouseMsg();

    @Query(value = "select * from housetype",nativeQuery = true)
    List<HouseType> getHouseType();

    @Query(value = "select new top.scarboroughcoral.c2c.model.dto.HouseMsgDTO(ht.houseTypeDesc,h.houseDesc," +
            "h.houseAddr,u.phone,h.price,o.orderTime) "+
            "from User u,UserOrder o,HouseType ht,House h " +
            "where h.houseTypeId=ht.houseTypeId and h.orderId=o.orderId and o.userId=?1")
    List<HouseMsgDTO> getRenterHouseMsg(Integer userID);
}
