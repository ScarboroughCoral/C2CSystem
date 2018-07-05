package top.scarboroughcoral.c2c.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "housetype")
public class HouseType {

    @Id
    @GeneratedValue
    @Column(name = "house_type_id")
    private Integer houseTypeId;


    @Column(name = "house_type")
    private String houseType;

    @Column(name = "house_type_description")
    private String houseTypeDesc;


    public HouseType(){

    }

    public HouseType(Integer houseTypeId, String houseType, String houseTypeDesc) {
        this.houseTypeId = houseTypeId;
        this.houseType = houseType;
        this.houseTypeDesc = houseTypeDesc;
    }

    public Integer getHouseTypeId() {
        return houseTypeId;
    }

    public void setHouseTypeId(Integer houseTypeId) {
        this.houseTypeId = houseTypeId;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getHouseTypeDesc() {
        return houseTypeDesc;
    }

    public void setHouseTypeDesc(String houseTypeDesc) {
        this.houseTypeDesc = houseTypeDesc;
    }
}
