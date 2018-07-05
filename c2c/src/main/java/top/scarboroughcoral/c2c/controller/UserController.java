package top.scarboroughcoral.c2c.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.scarboroughcoral.c2c.model.dto.AdminDTO;
import top.scarboroughcoral.c2c.model.dto.LoginDTO;
import top.scarboroughcoral.c2c.model.entity.User;
import top.scarboroughcoral.c2c.model.result.BaseResult;
import top.scarboroughcoral.c2c.service.UserService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    @GetMapping(value = "/users")
    public BaseResult<List<User>> getUsers(){
        BaseResult<List<User>> result = new BaseResult<>();
        userService.getUserList(result);
        return result;
    }

    @PostMapping(value = "/login")
    public BaseResult<LoginDTO> login(@RequestParam("loginName") String loginName,
                                      @RequestParam("password") String password
                                      ){
        BaseResult<LoginDTO> result = new BaseResult<>();
        userService.login(loginName,password,result);
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
    public BaseResult<AdminDTO> logout(@RequestParam("username") String  username,
                                       @RequestParam("password") String password){
        BaseResult<AdminDTO> result = new BaseResult<>();
        userService.logout(username,password,result);
        return result;
    }

    @PostMapping(value = "/addUser")
    public BaseResult<Object> addUser(@RequestParam("nickname") String loginName,
                                      @RequestParam("username") String username,
                                      @RequestParam("password") String password,
                                      @RequestParam(value = "phone",required = false) String phone,
                                      @RequestParam(value = "mail",required = false) String mail,
                                      @RequestParam(value = "address",required = false) String address,
                                      @RequestParam(value = "idCard",required = false) String idCard
                                      ){
        BaseResult<Object> result = new BaseResult<>();
        userService.addUser(loginName,username,password,phone,mail,address,idCard,result);
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
