package top.scarboroughcoral.c2c.model.entity.composite_id;

import javax.persistence.*;

@Entity
@Table(name = "housestatus")
public class HouseStatus {
    @Id
    @GeneratedValue
    @Column(name = "house_status_id")
    private Integer houseStatusId;
    @Column(name = "hosue_status")
    private String houseStatus;

    public HouseStatus() {
    }

    public Integer getHouseStatusId() {
        return houseStatusId;
    }

    public void setHouseStatusId(Integer houseStatusId) {
        this.houseStatusId = houseStatusId;
    }

    public String getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(String houseStatus) {
        this.houseStatus = houseStatus;
    }
}
