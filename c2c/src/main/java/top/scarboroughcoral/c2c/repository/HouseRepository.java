package top.scarboroughcoral.c2c.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.scarboroughcoral.c2c.model.dto.HouseMsgDTO;
import top.scarboroughcoral.c2c.model.dto.RenterHouseMsgDTO;
import top.scarboroughcoral.c2c.model.entity.House;
import top.scarboroughcoral.c2c.model.entity.HouseType;

import java.util.List;

public interface HouseRepository extends JpaRepository<House,Integer> {


    @Query(value = "select new top.scarboroughcoral.c2c.model.dto.HouseMsgDTO(h.houseId,ht.houseType,h.houseDesc," +
            "h.houseAddr,h.price) "+
            "from HouseType ht,House h " +
            "where h.houseTypeId=ht.houseTypeId and h.houseStatusId=1")
    List<HouseMsgDTO> getHouseMsg();

    @Query(value = "select * from housetype",nativeQuery = true)
    List<HouseType> getHouseType();

    @Query(value = "select new top.scarboroughcoral.c2c.model.dto.RenterHouseMsgDTO(ht.houseTypeDesc,hs.houseStatus," +
            "h.holdNumber,h.price,h.houseArea,h.houseDesc,h.houseAddr) " +
            "from House h,HousesOfRenters hor,HouseStatus hs,HouseType ht  " +
            "where hor.userId=?1 and hor.houseId=h.houseId and ht.houseTypeId=h.houseTypeId and hs.houseStatusId=h.houseStatusId")
    List<RenterHouseMsgDTO> getRenterHouseMsg(Integer userID);


}
