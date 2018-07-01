package top.scarboroughcoral.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import top.scarboroughcoral.vote.model.dto.VoteListItemBriefDTO;
import top.scarboroughcoral.vote.model.entity.Vote;
import top.scarboroughcoral.vote.model.dto.VoteListItemDTO;
import top.scarboroughcoral.vote.model.entity.Vote_Table;
import top.scarboroughcoral.vote.util.Constant;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Query(
            value = "SELECT " +
                    "new top.scarboroughcoral.vote.model.dto.VoteListItemBriefDTO(v.vote_id,v.title,vu.vote_status,v.vote_status) " +
                    "FROM " +
                    "Vote v, " +
                    "Votes_Of_Users vu, " +
                    "Vote_Type vt " +
                    "WHERE " +
                    "v.vote_id = vu.vote_id " +
                    "AND v.vote_type_id = vt.vote_type_id " +
                    "and vu.user_id=?1 and v.meeting_id=?2 and v.vote_status<>top.scarboroughcoral.vote.util.Constant.VOTE_STATUS_WAIT"

    )
    List<VoteListItemBriefDTO> getVoteListByUser_idAndMeeting_id(Integer user_id, Integer meeting_id);
    @Query(
            value = "select new top.scarboroughcoral.vote.model.dto.VoteListItemDTO(v.vote_id,v.meeting_id,vt.vote_type_name,v.title,v.sub_title,v.vote_status,v.is_abstain,v.max_pass_number, v.pass_rate_mother, v.min_agree, v.max_agree, v.pass_rate_child) " +
                    "from  Vote v  , Vote_Type vt where  v.vote_type_id=vt.vote_type_id " +
                    "and  v.meeting_id=?1"

    )
    List<VoteListItemDTO> fetchListByMeeting_id(Integer meeting_id);


    @Query(value = "select * from vote v, vote_table vt,table_row tr,table_row_column trc " +
            "where v.vote_id = vt.vote_id and trc.is_result=false and vt.vote_table_id=tr.vote_table_id and tr.row_id=trc.row_id and vt.vote_id=?1  ",nativeQuery = true)
    Vote getByVote_id(Integer vote_id);

    @Query(value = "select v.vote_id,v.title,v.is_abstain,v.max_pass_number,v.pass_rate_mother,v.pass_rate_child,vt.row_number,v.is_contain from vote v, vote_table vt " +
            "where v.vote_id = vt.vote_id and v.vote_id=?1",nativeQuery = true)
    Object getByVoteBrief(Integer vote_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE vote set vote.VOTE_STATUS = ?2 where vote.VOTE_ID = ?1",nativeQuery = true)
    Integer updateVoteStatus(Integer voteId,String status);
}
