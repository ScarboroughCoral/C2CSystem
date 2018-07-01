package top.scarboroughcoral.vote.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import top.scarboroughcoral.vote.util.Constant;

/**
 * Created by Hw Du on 2018/6/21.
 */
public class VoteListItemBriefDTO {
    private Integer voteId;
    private String voteTitle;
//    0.未投票、1.已投票、-1.已过期
    private Integer voteState;
    @JsonIgnore
    private String voteStatus;

    public VoteListItemBriefDTO() {
    }

    public VoteListItemBriefDTO(Integer voteId, String voteTitle, Integer voteState) {
        this.voteId = voteId;
        this.voteTitle = voteTitle;
        this.voteState = voteState;
    }

    public VoteListItemBriefDTO(Integer voteId, String voteTitle, Integer voteState, String voteStatus) {
        this.voteId = voteId;
        this.voteTitle = voteTitle;
        this.voteState = voteState;
        this.voteStatus = voteStatus;
        if (this.voteStatus.equals(Constant.VOTE_STATUS_END)){
            this.voteState=Constant.VOTE_STATUS_FAILURE;
        }
    }

    public String getVoteStatus() {
        return voteStatus;
    }

    public void setVoteStatus(String voteStatus) {
        this.voteStatus = voteStatus;
    }

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public String getVoteTitle() {
        return voteTitle;
    }

    public void setVoteTitle(String voteTitle) {
        this.voteTitle = voteTitle;
    }

    public Integer getVoteState() {
        return voteState;
    }

    public void setVoteState(Integer voteState) {
        this.voteState = voteState;
    }
}
