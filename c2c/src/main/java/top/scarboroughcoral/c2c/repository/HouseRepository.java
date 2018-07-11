package top.scarboroughcoral.c2c.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import top.scarboroughcoral.c2c.model.dto.RentHouseMsgDTO;
import top.scarboroughcoral.c2c.model.dto.HouseMsgDTO;
import top.scarboroughcoral.c2c.model.dto.RenterHouseMsgDTO;
import top.scarboroughcoral.c2c.model.entity.House;

import java.util.List;

public interface HouseRepository extends JpaRepository<House,Integer> {


    @Query(value = "select new top.scarboroughcoral.c2c.model.dto.HouseMsgDTO(h.houseId,ht.houseType,h.houseDesc," +
            "h.houseAddr,h.price) "+
            "from HouseType ht,House h " +
            "where h.houseTypeId=ht.houseTypeId and h.houseStatusId=1")
    List<HouseMsgDTO> getHouseMsg();

    @Query(value = "select * from house where house_id=?1",nativeQuery = true)
    House getHouse(Integer houseId);


    @Query(value = "select new top.scarboroughcoral.c2c.model.dto.HouseMsgDTO(h.houseId,ht.houseType,h.houseDesc," +
            "h.houseAddr,h.price) "+
            "from HouseType ht,House h " +
            "where h.houseTypeId=ht.houseTypeId and h.houseStatusId=1 and h.houseTypeId=?1")
    List<HouseMsgDTO> getHouseMsgByType(Integer houseTypeId);


    @Query(value = "select new top.scarboroughcoral.c2c.model.dto.RenterHouseMsgDTO(h.houseId,ht.houseTypeDesc,hs.houseStatus," +
            "h.holdNumber,h.price,h.houseArea,h.houseDesc,h.houseAddr,p.provinceDesc,c.cityDesc,d.districtDesc) " +
            "from House h,HousesOfRenters hor,HouseStatus hs,HouseType ht,Province p,City c,District d " +
            "where hor.userId=?1 and hor.houseId=h.houseId and ht.houseTypeId=h.houseTypeId and hs.houseStatusId=h.houseStatusId " +
            "and d.districtId=h.districtId and d.cityId=c.cityId and c.provinceId=p.provinceId")
    List<RenterHouseMsgDTO> getRenterHouseMsg(Integer userID);


    @Query(value = "update house set house_status_id=?2 where house_id=?1",nativeQuery = true)
    @Modifying
    Integer changeHouseState(Integer houseId,Integer houseStateId);


    @Query(value = "select new top.scarboroughcoral.c2c.model.dto.RentHouseMsgDTO(ht.houseType,h.houseAddr,p.provinceDesc,c.cityDesc,d.districtDesc," +
            "h.holdNumber,h.price,h.houseArea,h.houseStartTime,h.houseEndTime) " +
            "from Province p,City c,District d,HouseType ht,House h " +
            "where h.houseId=?1 and h.districtId=d.districtId and c.cityId=d.cityId and p.provinceId=c.provinceId and h.houseTypeId=ht.houseTypeId")
    RentHouseMsgDTO getHouseInfo(Integer houseId);
}
