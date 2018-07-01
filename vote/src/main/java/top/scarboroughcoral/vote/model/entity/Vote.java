package top.scarboroughcoral.vote.model.entity;

import top.scarboroughcoral.vote.model.dto.Vote_TableDTO;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static top.scarboroughcoral.vote.util.Constant.VOTE_STATUS_WAIT;

@Entity
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue
    private Integer vote_id;
    private Integer meeting_id;
    private Integer vote_type_id;
    private String title;
    private String sub_title;
    private String vote_status;
    private boolean is_abstain;
    private Integer is_contain;
    private Integer max_pass_number;
    private Integer pass_rate_mother;
    private Integer min_agree;
    private Integer max_agree;
    private Integer pass_rate_child;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "vote")
    private List<Vote_Table> vote_table;

    public Vote() {
    }

    public Vote(Integer vote_id) {
        this.vote_id = vote_id;
    }

    public Vote(Vote_TableDTO voteTable) {
        this.vote_type_id = voteTable.getVote_type_id();
        this.title = voteTable.getVoteName();
        this.sub_title = voteTable.getRuleDescription();
        this.vote_status = VOTE_STATUS_WAIT;
        this.is_abstain = voteTable.getIsAbstain() != 0;
        this.max_pass_number = voteTable.getMaxPassNumber();
        this.pass_rate_mother = voteTable.getPassRateMother();
        this.min_agree = voteTable.getMinAgree();
        this.max_agree = voteTable.getMaxAgree();
        this.pass_rate_child = voteTable.getPassRateChild();
        this.meeting_id = voteTable.getMeetingId();
        this.is_contain = voteTable.getIsContain();
    }

    public List<Vote_Table> getVote_table() {
        return vote_table;
    }

    public void setVote_table(List<Vote_Table> vote_table) {
        this.vote_table = vote_table;
    }

    public Integer getVote_id() {
        return vote_id;
    }

    public void setVote_id(Integer vote_id) {
        this.vote_id = vote_id;
    }

    public Integer getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(Integer meeting_id) {
        this.meeting_id = meeting_id;
    }

    public Integer getVote_type_id() {
        return vote_type_id;
    }

    public void setVote_type_id(Integer vote_type_id) {
        this.vote_type_id = vote_type_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getVote_status() {
        return vote_status;
    }

    public void setVote_status(String vote_status) {
        this.vote_status = vote_status;
    }

    public boolean isIs_abstain() {
        return is_abstain;
    }

    public void setIs_abstain(boolean is_abstain) {
        this.is_abstain = is_abstain;
    }

    public Integer getMax_pass_number() {
        return max_pass_number;
    }

    public void setMax_pass_number(Integer max_pass_number) {
        this.max_pass_number = max_pass_number;
    }

    public Integer getPass_rate_mother() {
        return pass_rate_mother;
    }

    public void setPass_rate_mother(Integer pass_rate_mother) {
        this.pass_rate_mother = pass_rate_mother;
    }

    public Integer getMin_agree() {
        return min_agree;
    }

    public void setMin_agree(Integer min_agree) {
        this.min_agree = min_agree;
    }

    public Integer getMax_agree() {
        return max_agree;
    }

    public void setMax_agree(Integer max_agree) {
        this.max_agree = max_agree;
    }

    public Integer getPass_rate_child() {
        return pass_rate_child;
    }

    public void setPass_rate_child(Integer pass_rate_child) {
        this.pass_rate_child = pass_rate_child;
    }

    public Integer getIs_contain() {
        return is_contain;
    }

    public void setIs_contain(Integer is_contain) {
        this.is_contain = is_contain;
    }
}
