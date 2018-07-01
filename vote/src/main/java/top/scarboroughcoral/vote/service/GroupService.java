package top.scarboroughcoral.vote.service;

import top.scarboroughcoral.vote.model.entity.User_Group;
import top.scarboroughcoral.vote.model.result.BaseResult;

public interface GroupService {
    public void findSpecific(Integer group_id, BaseResult<User_Group> result);
}
