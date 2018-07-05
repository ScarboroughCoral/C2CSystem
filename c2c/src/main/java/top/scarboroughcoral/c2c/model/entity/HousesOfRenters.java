package top.scarboroughcoral.c2c.model.entity;

import top.scarboroughcoral.c2c.model.entity.composite_id.HousesOfRentersId;

import javax.persistence.*;

@Entity
@Table(name = "housesofrenters")
@IdClass(HousesOfRentersId.class)
public class HousesOfRenters {
    @Id
    @Column(name = "user_id")
    private Integer userId;
    @Id
    @Column(name = "house_id")
    private Integer houseId;

    public HousesOfRenters(Integer userId, Integer houseId) {
        this.userId = userId;
        this.houseId = houseId;
    }

    public HousesOfRenters() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }
}
