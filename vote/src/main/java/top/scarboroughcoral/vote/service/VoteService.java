package top.scarboroughcoral.vote.service;

import top.scarboroughcoral.vote.model.dto.VoteListItemBriefDTO;
import top.scarboroughcoral.vote.model.dto.VoteListItemDTO;
import top.scarboroughcoral.vote.model.dto.Vote_TableDTO;
import top.scarboroughcoral.vote.model.entity.User_Vote_Result;
import top.scarboroughcoral.vote.model.entity.Vote;
import top.scarboroughcoral.vote.model.result.BaseResult;
import top.scarboroughcoral.vote.model.result.VoteResult;

import java.util.List;

public interface VoteService {
    public void getVoteList(Integer meeting_id, Integer user_id, BaseResult<List<VoteListItemBriefDTO>> result);
    public void fetchList(Integer meeting_id,BaseResult<List<VoteListItemDTO>> result);
    public void getVote(Integer vote_id, BaseResult<Vote> result);
    public void voteResult(Integer voteId, List<User_Vote_Result> user_vote_results, BaseResult<Object> result);

    void addVote(Vote_TableDTO voteTable, BaseResult<Object> result);

    void getVoteResult(Integer voteId, BaseResult<VoteResult> result);

    void changeVoteStatus(Integer voteId, String status, BaseResult<Object> result);

    void exportExcel(Integer voteId,BaseResult<String> result);
}
