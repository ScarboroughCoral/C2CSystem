package top.scarboroughcoral.c2c.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.scarboroughcoral.c2c.model.dto.EvaluationDTO;
import top.scarboroughcoral.c2c.model.entity.Evaluation;
import top.scarboroughcoral.c2c.model.result.BaseResult;
import top.scarboroughcoral.c2c.service.EvaluationService;

import java.util.List;

@RestController
@RequestMapping(value = "/evaluation")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @PostMapping("/set")
    public BaseResult<Object> setEvaluation(@RequestParam("userId") Integer userId,
                                            @RequestParam("houseId") Integer houseId,
                                            @RequestParam("evaluationStar") Integer star,
                                            @RequestParam("evaluationStr") String evaluationStr){

        BaseResult<Object> result = new BaseResult<>();
        evaluationService.setEvaluation(userId,houseId,star,evaluationStr,result);
        return result;
    }

    @GetMapping("/get")
    public BaseResult<List<EvaluationDTO>> getEvaluation(@RequestParam("houseId") Integer houseId){
        BaseResult<List<EvaluationDTO>> result = new BaseResult<>();
        evaluationService.getEvaluation(houseId,result);
        return result;
    }
}
