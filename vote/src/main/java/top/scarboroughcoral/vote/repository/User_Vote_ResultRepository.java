package top.scarboroughcoral.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.scarboroughcoral.vote.model.entity.User_Vote_Result;
import top.scarboroughcoral.vote.model.entity.composite_id.User_Vote_ResultId;

import java.util.List;

public interface User_Vote_ResultRepository extends JpaRepository<User_Vote_Result,User_Vote_ResultId> {
    @Query(value = "select trace.*,trc1.COLUMN_VALUE trc1_cv from  " +
            "(SELECT  v.VOTE_ID,tr.ROW_ID,tr.ROW_ORDER_NUMBER,trc.COLUMN_VALUE,uvr.RESULT,count(*) " +
            "FROM  vote v, vote_table vt, table_row tr, table_row_column trc  " +
            "LEFT JOIN user_vote_result uvr ON uvr.COLUMN_ID = trc.COLUMN_ID " +
            "WHERE  v.VOTE_ID = ?1 AND v.VOTE_ID = vt.VOTE_ID AND tr.VOTE_TABLE_ID = vt.VOTE_TABLE_ID " +
            "AND trc.ROW_ID = tr.ROW_ID AND trc.IS_RESULT = TRUE GROUP BY tr.ROW_ORDER_NUMBER,uvr.RESULT) " +
            "trace, table_row_column trc1 where trc1.row_id = trace.row_id" ,nativeQuery = true)
    List<Object> getVoteResult(Integer voteId);

    @Query(value = "select DISTINCT u.username from " +
            "(SELECT DISTINCT  trc.COLUMN_ID FROM  vote v,  vote_table vt,  table_row tr,  table_row_column trc " +
            "LEFT JOIN user_vote_result uvr ON uvr.COLUMN_ID = trc.COLUMN_ID " +
            "WHERE  v.VOTE_ID = ?1 AND v.VOTE_ID = vt.VOTE_ID AND tr.VOTE_TABLE_ID = vt.VOTE_TABLE_ID " +
            "AND trc.ROW_ID = tr.ROW_ID AND trc.IS_RESULT = TRUE) " +
            "COLUMN_IDs , user_vote_result uor, `user` u WHERE u.USER_ID = uor.USER_ID " +
            "and uor.COLUMN_ID = COLUMN_IDs.COLUMN_ID" ,nativeQuery = true)
    List<String> getVotedUsers(Integer voteId);
}
