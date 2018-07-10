package top.scarboroughcoral.c2c.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.scarboroughcoral.c2c.model.entity.HouseImg;

import java.util.List;

public interface ImgRepository extends JpaRepository<HouseImg,Integer>
{
    @Query(value = "select img_url from house_img where house_id=?1",nativeQuery = true)
    List<String> getImgUrl(Integer houseId);


}
