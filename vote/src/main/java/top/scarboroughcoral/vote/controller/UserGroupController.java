package top.scarboroughcoral.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.scarboroughcoral.vote.model.entity.User_Group;
import top.scarboroughcoral.vote.model.result.BaseResult;
import top.scarboroughcoral.vote.service.GroupService;

@RestController
@RequestMapping("/userGroup")
public class UserGroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/findSpecificGroup")
    public BaseResult<User_Group> findSpecificGroup(@RequestParam("groupId") Integer group_id){
        BaseResult<User_Group> result = new BaseResult<>();
        groupService.findSpecific(group_id,result);
        return  result;
    }
}
