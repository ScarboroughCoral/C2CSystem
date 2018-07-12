package top.scarboroughcoral.c2c.service;

import top.scarboroughcoral.c2c.model.dto.EvaluationDTO;
import top.scarboroughcoral.c2c.model.result.BaseResult;

import java.util.List;

public interface EvaluationService {

    void setEvaluation(Integer userId, Integer houseId, Integer evaluationStar, String evaluationStr, BaseResult<Object> result);
    void getEvaluation(Integer houseId, BaseResult<List<EvaluationDTO>> result);

}
