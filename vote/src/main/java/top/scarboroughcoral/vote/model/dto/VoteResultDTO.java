package top.scarboroughcoral.vote.model.dto;

import top.scarboroughcoral.vote.model.entity.User_Vote_Result;

import java.util.List;

public class VoteResultDTO {
    private Integer user_id;
    private List<User_Vote_Result> results;

    public VoteResultDTO() {
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public List<User_Vote_Result> getResults() {
        return results;
    }

    public void setResults(List<User_Vote_Result> results) {
        this.results = results;
    }
}
