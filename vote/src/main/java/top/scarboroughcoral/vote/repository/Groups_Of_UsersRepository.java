package top.scarboroughcoral.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.scarboroughcoral.vote.model.entity.Groups_Of_Users;
import top.scarboroughcoral.vote.model.entity.composite_id.Groups_Of_UsersId;

import java.util.List;

public interface Groups_Of_UsersRepository extends JpaRepository<Groups_Of_Users,Groups_Of_UsersId> {
    @Query(value = "select user_group_id from groups_of_users g where g.user_id=?1",nativeQuery = true)
    List<Integer> findByUser_id(Integer user_id);
}
