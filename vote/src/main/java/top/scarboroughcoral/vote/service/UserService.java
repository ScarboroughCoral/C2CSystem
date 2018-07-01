package top.scarboroughcoral.vote.service;

import top.scarboroughcoral.vote.model.dto.AdminDTO;
import top.scarboroughcoral.vote.model.result.BaseResult;
import top.scarboroughcoral.vote.model.dto.LoginDTO;
import top.scarboroughcoral.vote.model.result.UserListResult;

import java.util.List;

public interface UserService {

    public void login(String loginName, String password, String terminalId, BaseResult<LoginDTO> result);

    public void admin(String loginName, String password, BaseResult<AdminDTO> result);


    void addUser(String loginName, String username, String password, Integer userPriority, BaseResult<Object> result, Integer role);

    void listUser(Integer role, Integer meetingId, Integer voteId, BaseResult<List<UserListResult>> result);

    void changeUserMeeting(List<Integer> userIdList, Integer meetingId, BaseResult<Object> result);

    void changeUserVote(List<Integer> userIdList, Integer voteId, BaseResult<Object> result);
}
