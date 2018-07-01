package top.scarboroughcoral.vote.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.scarboroughcoral.vote.model.entity.User_Group;
import top.scarboroughcoral.vote.model.result.BaseResult;
import top.scarboroughcoral.vote.repository.GroupRepository;
import top.scarboroughcoral.vote.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Override
    public void findSpecific(Integer group_id, BaseResult<User_Group> result) {
        User_Group user_group = groupRepository.getById(group_id);
        System.out.println(user_group==null);
        if (user_group==null){
            result.setMessage("未找到对应此id的用户组别");
        }else {
            result.setSuccess(true);
            result.setMessage("获取用户组别成功");
            result.setData(user_group);
        }

    }
}
