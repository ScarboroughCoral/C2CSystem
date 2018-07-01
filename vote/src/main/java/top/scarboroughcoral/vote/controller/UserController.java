package top.scarboroughcoral.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.scarboroughcoral.vote.model.dto.AdminDTO;
import top.scarboroughcoral.vote.model.entity.User;
import top.scarboroughcoral.vote.model.result.BaseResult;
import top.scarboroughcoral.vote.model.dto.LoginDTO;
import top.scarboroughcoral.vote.model.result.UserListResult;
import top.scarboroughcoral.vote.repository.UserRepository;
import top.scarboroughcoral.vote.service.UserService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;




    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    @GetMapping(value = "/users")
    public BaseResult<List<User>> getUsers(){
        List<User> users = userRepository.findAll();
        BaseResult<List<User>> result = new BaseResult<>("test",true,users);
        return result;
    }

    @PostMapping(value = "/login")
    public BaseResult<LoginDTO> login(@RequestParam("loginName") String loginName,
                                      @RequestParam("password") String password,
                                      @RequestParam("terminalId") String terminalId){
        BaseResult<LoginDTO> result = new BaseResult<>();
        userService.login(loginName,password,terminalId,result);
        return result;
    }

    @RequestMapping(value = "/admin")
    public BaseResult<AdminDTO> admin(@RequestBody(required = true) AdminDTO adminDTO){
        BaseResult<AdminDTO> result = new BaseResult<>();
        userService.admin(adminDTO.getLoginName(),adminDTO.getPassword(),result);
        return result;
    }

    @GetMapping(value = "/info")
    public BaseResult<AdminDTO> adminInfo(@RequestParam(value = "token",required = false) String token){
        return null;
    }

    @PostMapping(value = "/logout")
    public BaseResult<AdminDTO> logout(){
        return null;
    }

    @PostMapping(value = "/addUser")
    public BaseResult<Object> addUser(@RequestParam("loginName") String loginName,
                                      @RequestParam("username") String username,
                                      @RequestParam("password") String password,
                                      @RequestParam("userPriority") Integer userPriority,
                                      @RequestParam("role") Integer role
                                      ){
        BaseResult<Object> result = new BaseResult<>();
        userService.addUser(loginName,username,password,userPriority,result,role);
        return result;
    }

    @PostMapping(value  = "/listUser")
    public BaseResult<List<UserListResult>> listUser(Integer role, Integer meetingId, Integer voteId){
        BaseResult<List<UserListResult>> result = new BaseResult<>();
        userService.listUser(role,meetingId,voteId,result);
        return result;
    }

    @PostMapping(value = "/changeUserMeeting")
    public BaseResult<Object> changeUserMeeting(@RequestBody List<Integer> userIdList, @RequestParam Integer meetingId){
        BaseResult<Object> result = new BaseResult<>();
        userService.changeUserMeeting(userIdList,meetingId,result);
        return result;
    }

    @PostMapping(value = "/changeUserVote")
    public BaseResult<Object> changeUserVote(@RequestBody List<Integer> userIdList, @RequestParam Integer voteId){
        BaseResult<Object> result = new BaseResult<>();
        userService.changeUserVote(userIdList,voteId,result);
        return result;
    }
}
