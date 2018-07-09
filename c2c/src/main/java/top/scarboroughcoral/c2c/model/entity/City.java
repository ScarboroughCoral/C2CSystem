package top.scarboroughcoral.c2c.model.entity;


import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue
    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "city_desc")
    private String cityDesc;

    @Column(name = "province_id")
    private Integer provinceId;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityDesc() {
        return cityDesc;
    }

    public void setCityDesc(String cityDesc) {
        this.cityDesc = cityDesc;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
}
