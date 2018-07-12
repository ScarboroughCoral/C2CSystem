package top.scarboroughcoral.c2c.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.scarboroughcoral.c2c.model.dto.EvaluationDTO;
import top.scarboroughcoral.c2c.model.entity.Evaluation;
import top.scarboroughcoral.c2c.model.result.BaseResult;
import top.scarboroughcoral.c2c.repository.EvaluationRepository;
import top.scarboroughcoral.c2c.service.EvaluationService;

import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Override
    public void setEvaluation(Integer userId, Integer houseId, Integer evaluationStar, String evaluationStr, BaseResult<Object> result) {
        Evaluation evaluation = new Evaluation(userId,houseId,evaluationStar,evaluationStr);
        evaluationRepository.save(evaluation);
        result.setSuccess(true);
        result.setMessage("评论成功");
    }

    @Override
    public void getEvaluation(Integer houseId, BaseResult<List<EvaluationDTO>> result) {
        List<EvaluationDTO> evaluationDTOList = evaluationRepository.getEvaluations(houseId);
        if(evaluationDTOList != null){
            result.setMessage("获取信息成功");
            result.setSuccess(true);
            result.setData(evaluationDTOList);
        }else{
            result.setMessage("该房间没有评价");
            result.setSuccess(false);
        }
    }
}
