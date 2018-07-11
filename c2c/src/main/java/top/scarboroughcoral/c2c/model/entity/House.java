package top.scarboroughcoral.c2c.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "house")
public class House {

    @Id
    @GeneratedValue
    @Column(name = "house_id")
    private Integer houseId;
    @Column(name = "house_type_id")
    private Integer houseTypeId;
    @Column(name = "house_status_id")
    private Integer houseStatusId;
    @Column(name = "hold_number")
    private Integer holdNumber;
    private Double price;
    @Column(name = "house_area")
    private Double houseArea;
    @Column(name = "house_desc")
    private String houseDesc;
    @Column(name = "house_addr")
    private String houseAddr;
    @Column(name = "house_district_id")
    private Integer districtId;
    @Column(name = "house_rent_date")
    private Date houseStartTime;
    @Column(name = "house_rent_end_date")
    private Date houseEndTime;

    public House(Integer houseId, Integer holdNumber, Double price, Double houseArea, String houseDesc, String houseAddr, Date houseStartTime, Date houseEndTime) {
        this.houseId = houseId;
        this.holdNumber = holdNumber;
        this.price = price;
        this.houseArea = houseArea;
        this.houseDesc = houseDesc;
        this.houseAddr = houseAddr;
        this.houseStartTime = houseStartTime;
        this.houseEndTime = houseEndTime;
    }

    public Date getHouseStartTime() {
        return houseStartTime;
    }

    public void setHouseStartTime(Date houseStartTime) {
        this.houseStartTime = houseStartTime;
    }

    public Date getHouseEndTime() {
        return houseEndTime;
    }

    public void setHouseEndTime(Date houseEndTime) {
        this.houseEndTime = houseEndTime;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public House() {
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getHouseTypeId() {
        return houseTypeId;
    }

    public void setHouseTypeId(Integer houseTypeId) {
        this.houseTypeId = houseTypeId;
    }

    public Integer getHouseStatusId() {
        return houseStatusId;
    }

    public void setHouseStatusId(Integer houseStatusId) {
        this.houseStatusId = houseStatusId;
    }


    public Integer getHoldNumber() {
        return holdNumber;
    }

    public void setHoldNumber(Integer holdNumber) {
        this.holdNumber = holdNumber;
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

    public String getHouseDesc() {
        return houseDesc;
    }

    public void setHouseDesc(String houseDesc) {
        this.houseDesc = houseDesc;
    }

    public String getHouseAddr() {
        return houseAddr;
    }

    public void setHouseAddr(String houseAddr) {
        this.houseAddr = houseAddr;
    }
}
