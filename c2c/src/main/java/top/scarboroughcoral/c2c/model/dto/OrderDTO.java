package top.scarboroughcoral.c2c.model.dto;

import java.util.Date;

public class OrderDTO {

    private String username;
    private String orderStateDesc;
    private String houseType;
    private Double price;
    private Double houseArea;
    private String houseAddr;
    private String houseDesc;
    private String provinceDesc;
    private String cityDesc;
    private String districtDesc;
    private Date orderTime;

    public OrderDTO(){
    }

    public OrderDTO(String username, String orderStateDesc, String houseType, Double price, Double houseArea, String houseAddr, String houseDesc, String provinceDesc, String cityDesc, String districtDesc, Date orderTime) {
        this.username = username;
        this.orderStateDesc = orderStateDesc;
        this.houseType = houseType;
        this.price = price;
        this.houseArea = houseArea;
        this.houseAddr = houseAddr;
        this.houseDesc = houseDesc;
        this.provinceDesc = provinceDesc;
        this.cityDesc = cityDesc;
        this.districtDesc = districtDesc;
        this.orderTime = orderTime;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderStateDesc() {
        return orderStateDesc;
    }

    public void setOrderStateDesc(String orderStateDesc) {
        this.orderStateDesc = orderStateDesc;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(Double houseArea) {
        this.houseArea = houseArea;
    }

    public String getHouseAddr() {
        return houseAddr;
    }

    public void setHouseAddr(String houseAddr) {
        this.houseAddr = houseAddr;
    }

    public String getHouseDesc() {
        return houseDesc;
    }

    public void setHouseDesc(String houseDesc) {
        this.houseDesc = houseDesc;
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
}
