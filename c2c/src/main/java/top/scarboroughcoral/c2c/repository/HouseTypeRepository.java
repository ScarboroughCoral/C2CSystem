package top.scarboroughcoral.c2c.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.scarboroughcoral.c2c.model.entity.HouseType;

import java.util.List;

public interface HouseTypeRepository extends JpaRepository<HouseType,Integer> {

    @Query(value = "select * from housetype",nativeQuery = true)
    List<HouseType> getHouseType();
}
