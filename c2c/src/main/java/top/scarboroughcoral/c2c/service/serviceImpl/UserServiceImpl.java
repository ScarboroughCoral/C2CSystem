package top.scarboroughcoral.c2c.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.scarboroughcoral.c2c.model.dto.AdminDTO;
import top.scarboroughcoral.c2c.model.dto.LoginDTO;
import top.scarboroughcoral.c2c.model.entity.User;
import top.scarboroughcoral.c2c.repository.UserRepository;
import top.scarboroughcoral.c2c.util.Constant;
import top.scarboroughcoral.c2c.model.result.BaseResult;
import top.scarboroughcoral.c2c.service.UserService;
import top.scarboroughcoral.c2c.util.MD5Util;

import java.util.List;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void login(String loginName, String password, BaseResult<LoginDTO> result) {
        User user = userRepository.findByLogin(loginName,password);
        if(user != null){
            user.setToken(new Date().getTime()+"");
            userRepository.save(user);
            LoginDTO loginDTO = new LoginDTO(user.getUsername(),user.getUserId(),user.getName(),user.getToken());
            result.setData(loginDTO);
            result.setSuccess(true);
            result.setMessage("登陆成功");
        }else{
            result.setMessage("请检查用户名和密码");
        }
    }

    @Override
    public void logout(Integer userId, BaseResult<AdminDTO> result) {
        User user = userRepository.getOne(userId);
        user.setToken(null);
        userRepository.save(user);
        result.setSuccess(true);
        result.setMessage("登出成功");
//        result.setData(new AdminDTO(username));
    }

    @Override
    public void admin(String loginName, String password, BaseResult<AdminDTO> result) {
    }

    @Override
    public void addUser(String loginName, String username, String password, String phone, String mail, String address, String idCard, BaseResult<Object> result) {
        Date date = new Date();
        userRepository.save(new User(username, MD5Util.getMD5(password), loginName, phone, mail, address, date, idCard, false, "notOnline"));
        result.setMessage("成功注册");
        result.setSuccess(true);
    }

    @Override
    public void changeUserInfo(Integer userId, String phone, String identify, String address, String mail, String name, String password, BaseResult<Object> result) {
        User user = userRepository.getUser(userId);
        if(user == null){
            result.setMessage("用户不存在");
            result.setSuccess(false);
            return;
        }

        if(phone != null){
            user.setPhone(phone);
        }

        if(identify != null){
            user.setIDCard(identify);
        }

        if(address != null){
            user.setAddress(address);
        }

        if(mail != null){
            user.setMail(mail);
        }

        if(name != null){
            user.setName(name);
        }

        if(password != null){
            user.setPassword(MD5Util.getMD5(password));
        }

        userRepository.save(user);

        result.setMessage("修改成功");
        result.setSuccess(true);

    }


    @Override
    public void getUserList(BaseResult<List<User>> result) {
//        List<Object> objList = userRepository.findByRole(role,meetingId, voteId);
//        List<UserListResult> userList = objList.stream().map(UserListResult::new)
//                .collect(Collectors.toList());
        List<User> userList = userRepository.getUserList();
        result.construct(Constant.RESULT_BASE_QUERY_SUCCESS_MESSAGE, true, userList);
    }

    @Override
    public void changeUserMeeting(List<Integer> userIdList, Integer meetingId, BaseResult<Object> result) {

    }

    @Override
    public void changeUserVote(List<Integer> userIdList, Integer voteId, BaseResult<Object> result) {

    }
}
