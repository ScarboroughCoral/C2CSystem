package top.scarboroughcoral.c2c.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "evaluation")
public class Evaluation {

    @Id
    @GeneratedValue
    @Column(name = "evaluation_id")
    private Integer evaluationId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "house_id")
    private Integer houseId;
    @Column(name = "evaluation_star")
    private Integer evaluationStar;
    @Column(name = "evaluation")
    private String evaluationStr;

    public Evaluation(){

    }

    public Evaluation(Integer userId, Integer houseId, Integer evaluationStar, String evaluationStr) {
        this.userId = userId;
        this.houseId = houseId;
        this.evaluationStar = evaluationStar;
        this.evaluationStr = evaluationStr;
    }

    public Evaluation(Integer evaluationId, Integer userId, Integer houseId, Integer evaluationStar, String evaluationStr) {
        this.evaluationId = evaluationId;
        this.userId = userId;
        this.houseId = houseId;
        this.evaluationStar = evaluationStar;
        this.evaluationStr = evaluationStr;
    }

    public Integer getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Integer evaluationId) {
        this.evaluationId = evaluationId;
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

    public Integer getEvaluationStar() {
        return evaluationStar;
    }

    public void setEvaluationStar(Integer evaluationStar) {
        this.evaluationStar = evaluationStar;
    }

    public String getEvaluationStr() {
        return evaluationStr;
    }

    public void setEvaluationStr(String evaluationStr) {
        this.evaluationStr = evaluationStr;
    }
}
