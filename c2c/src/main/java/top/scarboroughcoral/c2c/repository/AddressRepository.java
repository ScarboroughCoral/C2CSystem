package top.scarboroughcoral.c2c.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.scarboroughcoral.c2c.model.dto.AddressDTO;
import top.scarboroughcoral.c2c.model.dto.HouseMsgDTO;
import top.scarboroughcoral.c2c.model.entity.District;

import java.util.List;

public interface AddressRepository extends JpaRepository<District,Integer> {

    @Query(value = "select new top.scarboroughcoral.c2c.model.dto.AddressDTO(p.provinceDesc,c.cityDesc,d.districtDesc) " +
            "from City c,District d,Province p " +
            "where c.provinceId=p.provinceId and c.cityId=d.cityId and d.districtId=?1")
    AddressDTO getAddress(Integer districtId);

    @Query(value = "select new top.scarboroughcoral.c2c.model.dto.AddressDTO(p.provinceDesc,c.cityDesc,d.districtDesc) " +
            "from City c,District d,Province p,House h " +
            "where c.provinceId=p.provinceId and c.cityId=d.cityId and d.districtId=h.districtId and h.houseId=?1")
    AddressDTO getHouseAddress(Integer houseId);

    @Query(value = "select * from district",nativeQuery = true)
    List<District> getDistrict();


    @Query(value = "select new top.scarboroughcoral.c2c.model.dto.HouseMsgDTO(ht.houseType,h.houseDesc," +
            "h.houseAddr,h.price) "+
            "from HouseType ht,House h,Province p,City c,District d " +
            "where h.houseTypeId=ht.houseTypeId and h.houseStatusId=1 and c.provinceId=p.provinceId and c.cityId=d.cityId and d.districtId=h.districtId " +
            "and p.provinceId=?1")
    List<HouseMsgDTO> searchHouseByProvince(Integer provinceId);

    @Query(value = "select new top.scarboroughcoral.c2c.model.dto.HouseMsgDTO(ht.houseType,h.houseDesc," +
            "h.houseAddr,h.price) "+
            "from HouseType ht,House h,City c,District d " +
            "where h.houseTypeId=ht.houseTypeId and h.houseStatusId=1 and c.provinceId=?1 and c.cityId=d.cityId and d.districtId=h.districtId")
    List<HouseMsgDTO> searchHouseByCity(Integer cityId);

    @Query(value = "select new top.scarboroughcoral.c2c.model.dto.HouseMsgDTO(ht.houseType,h.houseDesc," +
            "h.houseAddr,h.price) "+
            "from HouseType ht,House h,Province p,City c,District d " +
            "where h.houseTypeId=ht.houseTypeId and h.houseStatusId=1 and d.districtId=?1 and d.districtId=h.districtId")
    List<HouseMsgDTO> searchHouseByDistrict(Integer provinceId);
}
