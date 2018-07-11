package top.scarboroughcoral.c2c.model.dto;

public class RenterHouseMsgDTO {

    private Integer houseId;
    private String houseTypeDes;
    private String houseStateDes;
    private Integer holdNum;
    private Double price;
    private Double houseArea;
    private String houseDecs;
    private String houseAddr;
    private String provinceDesc;
    private String cityDesc;
    private String districtDesc;

    public RenterHouseMsgDTO(Integer houseId, String houseTypeDes, String houseStateDes, Integer holdNum, Double price, Double houseArea, String houseDecs, String houseAddr, String provinceDesc, String cityDesc, String districtDesc) {
        this.houseId = houseId;
        this.houseTypeDes = houseTypeDes;
        this.houseStateDes = houseStateDes;
        this.holdNum = holdNum;
        this.price = price;
        this.houseArea = houseArea;
        this.houseDecs = houseDecs;
        this.houseAddr = houseAddr;
        this.provinceDesc = provinceDesc;
        this.cityDesc = cityDesc;
        this.districtDesc = districtDesc;
    }

    public RenterHouseMsgDTO(Integer houseId, String houseTypeDes, String houseStateDes, Integer holdNum, Double price, Double houseArea, String houseDecs, String houseAddr) {
        this.houseId = houseId;
        this.houseTypeDes = houseTypeDes;
        this.houseStateDes = houseStateDes;
        this.holdNum = holdNum;
        this.price = price;
        this.houseArea = houseArea;
        this.houseDecs = houseDecs;
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

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public RenterHouseMsgDTO(String houseTypeDes, String houseStateDes, Integer holdNum, Double price, Double houseArea, String houseDecs, String houseAddr) {
        this.houseTypeDes = houseTypeDes;
        this.houseStateDes = houseStateDes;
        this.holdNum = holdNum;
        this.price = price;
        this.houseArea = houseArea;
        this.houseDecs = houseDecs;
        this.houseAddr = houseAddr;
    }



    public String getHouseTypeDes() {
        return houseTypeDes;
    }

    public void setHouseTypeDes(String houseTypeDes) {
        this.houseTypeDes = houseTypeDes;
    }

    public String getHouseStateDes() {
        return houseStateDes;
    }

    public void setHouseStateDes(String houseStateDes) {
        this.houseStateDes = houseStateDes;
    }

    public Integer getHoldNum() {
        return holdNum;
    }

    public void setHoldNum(Integer holdNum) {
        this.holdNum = holdNum;
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

    public String getHouseDecs() {
        return houseDecs;
    }

    public void setHouseDecs(String houseDecs) {
        this.houseDecs = houseDecs;
    }

    public String getHouseAddr() {
        return houseAddr;
    }

    public void setHouseAddr(String houseAddr) {
        this.houseAddr = houseAddr;
    }

}
