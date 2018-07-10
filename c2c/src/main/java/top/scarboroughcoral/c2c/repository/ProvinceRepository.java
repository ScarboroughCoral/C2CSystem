package top.scarboroughcoral.c2c.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.scarboroughcoral.c2c.model.entity.Province;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<Province,Integer> {

    @Query(value = "select * from province",nativeQuery = true)
    List<Province> getProvince();
}
