package top.scarboroughcoral.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import top.scarboroughcoral.vote.model.dto.VoteListItemBriefDTO;
import top.scarboroughcoral.vote.model.dto.VoteListItemDTO;
import top.scarboroughcoral.vote.model.dto.VoteResultDTO;
import top.scarboroughcoral.vote.model.dto.Vote_TableDTO;
import top.scarboroughcoral.vote.model.entity.Table_Row_Column;
import top.scarboroughcoral.vote.model.entity.User_Vote_Result;
import top.scarboroughcoral.vote.model.entity.Vote;
import top.scarboroughcoral.vote.model.entity.Vote_Table;
import top.scarboroughcoral.vote.model.result.BaseResult;
import top.scarboroughcoral.vote.model.result.VoteResult;
import top.scarboroughcoral.vote.service.VoteService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;


    @GetMapping("/getVoteList")
    public BaseResult<List<VoteListItemBriefDTO>> getVoteList(@RequestParam("meetingId") Integer meeting_id, @RequestParam("userId") Integer user_id){
        BaseResult<List<VoteListItemBriefDTO>> result = new BaseResult<>();
        voteService.getVoteList(meeting_id,user_id,result);
        return result;
    }

    @GetMapping("/list")
    public BaseResult<List<VoteListItemDTO>> fectchList(@RequestParam("meetingId") Integer meeting_id){
        BaseResult<List<VoteListItemDTO>> result = new BaseResult<>();
        voteService.fetchList(meeting_id,result);
        return result;
    }

    @GetMapping("/getVoteTable")
    public BaseResult<Vote> getVote(@RequestParam("voteId") Integer vote_id){
        BaseResult<Vote> result = new BaseResult<>();
        voteService.getVote(vote_id,result);
        return result;
    }

    @PostMapping(value = "/voteResult",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResult<Object> voteResult(@RequestParam("voteId") Integer voteId,@RequestBody VoteResultDTO voteResultDTO, HttpServletRequest request){
        List<User_Vote_Result> user_vote_results = voteResultDTO.getResults();
        Integer user_id = voteResultDTO.getUser_id();
        for (int i = 0; i < user_vote_results.size(); i++) {
            user_vote_results.get(i).setUser_id(user_id);
        }
        BaseResult<Object> result = new BaseResult<>();

        voteService.voteResult(voteId,user_vote_results,result);
        return result;
    }

    @PostMapping(value = "/addVote",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResult<Object> addVote(@RequestBody Vote_TableDTO voteTable){
        System.out.print(voteTable);
        BaseResult<Object> result = new BaseResult<>();
        voteService.addVote(voteTable,result);
        return result;
    }

    @PostMapping(value = "/getVoteResult")
    public BaseResult<VoteResult> getVoteResult(@RequestParam("voteId") Integer voteId){
        BaseResult<VoteResult> result = new BaseResult<>();
        voteService.getVoteResult(voteId,result);
        return result;
    }

    @PostMapping(value = "/changeVoteStatus")
    public BaseResult<Object> changeVoteStatus(@RequestParam("voteId") Integer voteId, @RequestParam("status") String status){
        BaseResult<Object> result = new BaseResult<>();
        voteService.changeVoteStatus(voteId, status,result);
        return result;
    }

    @GetMapping(value = "/exportResult")
    public BaseResult<String>  exportExcel(@RequestParam("voteId") Integer voteId){
        BaseResult<String> result = new BaseResult<>();
        voteService.exportExcel(voteId,result);
        return result;
    }
}
