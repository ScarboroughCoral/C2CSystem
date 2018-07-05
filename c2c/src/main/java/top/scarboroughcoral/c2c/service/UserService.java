package top.scarboroughcoral.c2c.service;

import top.scarboroughcoral.c2c.model.dto.AdminDTO;
import top.scarboroughcoral.c2c.model.dto.LoginDTO;
import top.scarboroughcoral.c2c.model.entity.User;
import top.scarboroughcoral.c2c.model.result.BaseResult;
import top.scarboroughcoral.c2c.model.result.UserListResult;

import java.util.List;

public interface UserService {

    void login(String loginName, String password, BaseResult<LoginDTO> result);

    void logout(String username,String password, BaseResult<AdminDTO> result);

    void admin(String loginName, String password, BaseResult<AdminDTO> result);

    void addUser(String loginName, String username, String password, String phone,String mail,String address,String idCard,BaseResult<Object> result);

    void getUserList(BaseResult<List<User>> result);

    void changeUserMeeting(List<Integer> userIdList, Integer meetingId, BaseResult<Object> result);

    void changeUserVote(List<Integer> userIdList, Integer voteId, BaseResult<Object> result);
}
