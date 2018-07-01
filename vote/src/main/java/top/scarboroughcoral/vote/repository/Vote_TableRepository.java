package top.scarboroughcoral.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.scarboroughcoral.vote.model.entity.Vote_Table;

import java.util.List;

public interface Vote_TableRepository extends JpaRepository<Vote_Table,Integer> {

    @Query(value = "select * from Vote_Table vt,Table_Row tr,Table_Row_Column trc " +
            "where vt.vote_table_id=tr.vote_table_id and tr.row_id=trc.row_id and vt.vote_id=?1",nativeQuery = true)
    Vote_Table getByVote_id(Integer vote_id);
}
