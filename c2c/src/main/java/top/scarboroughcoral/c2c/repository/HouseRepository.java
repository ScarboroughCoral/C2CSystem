package top.scarboroughcoral.c2c.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.scarboroughcoral.c2c.model.dto.HouseMsgDTO;

import java.util.List;

public interface HouseRepository extends JpaRepository<HouseMsgDTO,Integer> {


    @Query(value = "select new top.scarboroughcoral.c2c.model.dto.HouseMsgDTO(ht.house_type_description,) "+
            "from user u,userorder o,housetype ht,house h" +
            "where h.house_type_id=ht.house_type_id and h.order_id=o.order_id and o.user_id=u.user_id")
    List<HouseMsgDTO> getHouseMsg();
}
