package top.scarboroughcoral.c2c.model.dto;

public class EvaluationDTO {

    private String name;

    private Integer evaluationStar;
    private String evaluationStr;

    public EvaluationDTO(){

    }

    public EvaluationDTO(String name, Integer evaluationStar, String evaluationStr) {
        this.name = name;
        this.evaluationStar = evaluationStar;
        this.evaluationStr = evaluationStr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
