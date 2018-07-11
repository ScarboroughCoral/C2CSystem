package top.scarboroughcoral.c2c.model.dto;

import java.util.Date;

public class RentHouseMsgDTO {


    private String houseTypeDes;
    private String houseAddr;
    private String provinceDesc;
    private String cityDesc;
    private String districtDesc;
    private Integer phoneNumber;
    private Double price;
    private Double area;
    private Date startTime;
    private Date endTime;

    public RentHouseMsgDTO(){

    }

    public RentHouseMsgDTO(String houseTypeDes, String houseAddr, String provinceDesc, String cityDesc, String districtDesc, Integer phoneNumber, Double price, Double area, Date startTime, Date endTime) {
        this.houseTypeDes = houseTypeDes;
        this.houseAddr = houseAddr;
        this.provinceDesc = provinceDesc;
        this.cityDesc = cityDesc;
        this.districtDesc = districtDesc;
        this.phoneNumber = phoneNumber;
        this.price = price;
        this.area = area;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getHouseTypeDes() {
        return houseTypeDes;
    }

    public void setHouseTypeDes(String houseTypeDes) {
        this.houseTypeDes = houseTypeDes;
    }

    public String getHouseAddr() {
        return houseAddr;
    }

    public void setHouseAddr(String houseAddr) {
        this.houseAddr = houseAddr;
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

    public String getDistrictDesc() {
        return districtDesc;
    }

    public void setDistrictDesc(String districtDesc) {
        this.districtDesc = districtDesc;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
