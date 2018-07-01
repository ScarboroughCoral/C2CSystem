package top.scarboroughcoral.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.scarboroughcoral.vote.model.entity.User_Group;

public interface GroupRepository extends JpaRepository<User_Group,Integer> {

    @Query(value = "select * from user_group where user_group_id=?1",nativeQuery = true)
    User_Group getById(Integer id);

}
