package top.scarboroughcoral.c2c.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.scarboroughcoral.c2c.model.dto.EvaluationDTO;
import top.scarboroughcoral.c2c.model.entity.Evaluation;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation ,Integer> {

    @Query("select new top.scarboroughcoral.c2c.model.dto.EvaluationDTO(u.name,e.evaluationStar,e.evaluationStr) " +
            "from top.scarboroughcoral.c2c.model.entity.Evaluation e,User u " +
            "where e.houseId=?1 and e.userId=u.userId")
    List<EvaluationDTO> getEvaluations(Integer houseId);
}
