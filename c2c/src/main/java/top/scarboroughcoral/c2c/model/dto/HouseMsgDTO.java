package top.scarboroughcoral.c2c.model.dto;

import java.util.Date;

public class HouseMsgDTO {

    private Integer houseID;

    private String houseTypeDes;
    private String houseDes;
    private String houseAddr;
    private Double houseMoney;

    private String provinceDesc;
    private String cityDesc;
    private String districtDess;


    public HouseMsgDTO(){

    }

    public HouseMsgDTO(Integer houseID, String houseTypeDes, String houseDes, String houseAddr, Double houseMoney, String provinceDesc, String cityDesc, String districtDess) {
        this.houseID = houseID;
        this.houseTypeDes = houseTypeDes;
        this.houseDes = houseDes;
        this.houseAddr = houseAddr;
        this.houseMoney = houseMoney;
        this.provinceDesc = provinceDesc;
        this.cityDesc = cityDesc;
        this.districtDess = districtDess;
    }

    public HouseMsgDTO(Integer houseID, String houseTypeDes, String houseDes, String houseAddr, Double houseMoney) {
        this.houseID = houseID;
        this.houseTypeDes = houseTypeDes;
        this.houseDes = houseDes;
        this.houseAddr = houseAddr;
        this.houseMoney = houseMoney;
    }

    public String getProvinceDesc() {
        return provinceDesc;
    }

    public void setProvinceDesc(String provinceDesc) {
        this.provinceDesc = provinceDesc;
    }

    public String getCityDesc() {
        return cityDesc;
    }

    public void setCityDesc(String cityDesc) {
        this.cityDesc = cityDesc;
    }

    public String getDistrictDess() {
        return districtDess;
    }

    public void setDistrictDess(String districtDess) {
        this.districtDess = districtDess;
    }

    public Integer getHouseID() {
        return houseID;
    }

    public void setHouseID(Integer houseID) {
        this.houseID = houseID;
    }

    public HouseMsgDTO(String houseTypeDes, String houseDes, String houseAddr, Double houseMoney) {
        this.houseTypeDes = houseTypeDes;
        this.houseDes = houseDes;
        this.houseAddr = houseAddr;
        this.houseMoney = houseMoney;
    }

    public String getHouseTypeDes() {
        return houseTypeDes;
    }

    public void setHouseTypeDes(String houseTypeDes) {
        this.houseTypeDes = houseTypeDes;
    }

    public String getHouseDes() {
        return houseDes;
    }

    public void setHouseDes(String houseDes) {
        this.houseDes = houseDes;
    }

    public String getHouseAddr() {
        return houseAddr;
    }

    public void setHouseAddr(String houseAddr) {
        this.houseAddr = houseAddr;
    }


    public Double getHouseMoney() {
        return houseMoney;
    }

    public void setHouseMoney(Double houseMoney) {
        this.houseMoney = houseMoney;
    }

}
