package top.scarboroughcoral.c2c.model.entity.composite_id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "agreement")
public class Agreement {
    @Id
    @Column(name = "agreement_id")
    private Integer agreementId;
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "agreement_url")
    private String agreementUrl;

    public Agreement() {
    }

    public Integer getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(Integer agreementId) {
        this.agreementId = agreementId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getAgreementUrl() {
        return agreementUrl;
    }

    public void setAgreementUrl(String agreementUrl) {
        this.agreementUrl = agreementUrl;
    }
}
