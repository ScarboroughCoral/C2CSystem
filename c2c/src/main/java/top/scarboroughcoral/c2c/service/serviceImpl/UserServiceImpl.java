package top.scarboroughcoral.c2c.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.scarboroughcoral.c2c.model.dto.AdminDTO;
import top.scarboroughcoral.c2c.model.dto.LoginDTO;
import top.scarboroughcoral.c2c.model.entity.Roles_Of_Users;
import top.scarboroughcoral.c2c.model.entity.User;
import top.scarboroughcoral.c2c.repository.UserRepository;
import top.scarboroughcoral.c2c.util.Constant;
import top.scarboroughcoral.c2c.model.result.BaseResult;
import top.scarboroughcoral.c2c.model.result.UserListResult;
import top.scarboroughcoral.c2c.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void login(String loginName, String password, String terminalId, BaseResult<LoginDTO> result) {

    }

    @Override
    public void admin(String loginName, String password, BaseResult<AdminDTO> result) {
        User user = userRepository.findByAdmin(loginName,password);
        if (user!=null){
            AdminDTO adminDTO = new AdminDTO(user.getUsername());
            result.setData(adminDTO);
            result.setSuccess(true);
            result.setMessage("登陆成功！");
        }else {
            result.setMessage("请检查用户名和密码！");
        }
    }

    @Override
    public void addUser(String loginName, String username, String password, Integer userPriority, BaseResult<Object> result, Integer role) {

    }

    @Override
    public void listUser(Integer role, Integer meetingId, Integer voteId, BaseResult<List<UserListResult>> result) {
        List<Object> objList = userRepository.findByRole(role,meetingId, voteId);
        List<UserListResult> userList = objList.stream().map(UserListResult::new)
                .collect(Collectors.toList());
        result.construct(Constant.RESULT_BASE_QUERY_SUCCESS_MESSAGE, true, userList);
    }

    @Override
    public void changeUserMeeting(List<Integer> userIdList, Integer meetingId, BaseResult<Object> result) {

    }

    @Override
    public void changeUserVote(List<Integer> userIdList, Integer voteId, BaseResult<Object> result) {

    }
}
