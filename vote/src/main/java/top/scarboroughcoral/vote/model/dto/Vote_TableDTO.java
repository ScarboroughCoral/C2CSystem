package top.scarboroughcoral.vote.model.dto;


import top.scarboroughcoral.vote.model.entity.Table_Row;
import top.scarboroughcoral.vote.model.entity.Vote;

import java.util.List;

public class Vote_TableDTO {
    private String voteName;
    private Integer vote_type_id;
    private Integer meetingId;
    private String ruleDescription;
    private Integer isAbstain;
    private Integer isContain;
    private Integer rowNumber;
    private Integer columnNumber;
    private Integer minAgree;
    private Integer maxAgree;
    private Integer passRateMother;
    private Integer passRateChild;
    private Integer maxPassNumber;
    private VoteTableContentDTO table;

    public Vote_TableDTO() {
    }

    public String getVoteName() {
        return voteName;
    }

    public void setVoteName(String voteName) {
        this.voteName = voteName;
    }

    public Integer getVote_type_id() {
        return vote_type_id;
    }

    public void setVote_type_id(Integer vote_type_id) {
        this.vote_type_id = vote_type_id;
    }

    public String getRuleDescription() {
        return ruleDescription;
    }

    public void setRuleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription;
    }

    public Integer getIsAbstain() {
        return isAbstain;
    }

    public void setIsAbstain(Integer isAbstain) {
        this.isAbstain = isAbstain;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Integer getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(Integer columnNumber) {
        this.columnNumber = columnNumber;
    }

    public Integer getMinAgree() {
        return minAgree;
    }

    public void setMinAgree(Integer minAgree) {
        this.minAgree = minAgree;
    }

    public Integer getMaxAgree() {
        return maxAgree;
    }

    public void setMaxAgree(Integer maxAgree) {
        this.maxAgree = maxAgree;
    }

    public Integer getPassRateMother() {
        return passRateMother;
    }

    public void setPassRateMother(Integer passRateMother) {
        this.passRateMother = passRateMother;
    }

    public Integer getPassRateChild() {
        return passRateChild;
    }

    public void setPassRateChild(Integer passRateChild) {
        this.passRateChild = passRateChild;
    }

    public Integer getMaxPassNumber() {
        return maxPassNumber;
    }

    public void setMaxPassNumber(Integer maxPassNumber) {
        this.maxPassNumber = maxPassNumber;
    }

    public VoteTableContentDTO getTable() {
        return table;
    }

    public void setTable(VoteTableContentDTO table) {
        this.table = table;
    }

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public Integer getIsContain() {
        return isContain;
    }

    public void setIsContain(Integer isContain) {
        this.isContain = isContain;
    }
}
