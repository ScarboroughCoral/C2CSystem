package top.scarboroughcoral.c2c.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.scarboroughcoral.c2c.model.entity.City;

import java.util.List;

public interface CityRepository extends JpaRepository<City,Integer> {

    @Query(value = "select * from city",nativeQuery = true)
    List<City> getCity();
}
