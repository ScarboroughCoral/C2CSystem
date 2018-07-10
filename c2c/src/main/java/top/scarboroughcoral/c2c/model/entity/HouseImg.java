package top.scarboroughcoral.c2c.model.entity;




import javax.persistence.*;

@Entity
@Table(name = "house_img")
public class HouseImg {

    @Id
    @GeneratedValue
    @Column(name = "img_id")
    private Integer imgID;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "house_id")
    private Integer houseId;


    public HouseImg(String imgUrl, Integer houseId) {
        this.imgUrl = imgUrl;
        this.houseId = houseId;
    }

    public Integer getImgID() {
        return imgID;
    }

    public void setImgID(Integer imgID) {
        this.imgID = imgID;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }
}
