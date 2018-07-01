package top.scarboroughcoral.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.scarboroughcoral.vote.model.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value = "select * from USER u where u.loginName=?1 and u.password=md5(?2)",nativeQuery = true)
    User findByLogin(String loginName,String password);

    @Query(value = "select * from USER u where u.loginName=?1",nativeQuery = true)
    User findByLoginName(String loginName);

    @Query(value = "select u.* from user u,roles_of_users rou,role r " +
            "where r.role_id = rou.role_id and rou.user_id = u.user_id and r.role_name = '超级管理员' " +
            "and u.loginName = ?1 and u.password = md5(?2) ",
            nativeQuery = true)
    User findByAdmin(String loginName,String password);

    @Query(value = "SELECT  u.*, uom.meeting_id, temp.vi  FROM  user u " +
            "LEFT JOIN users_of_meeting uom ON u.user_id = uom.user_id AND uom.meeting_id = ?2 " +
            "LEFT JOIN ( select v.MEETING_ID mi, vou.USER_ID ui, v.VOTE_ID vi from votes_of_users vou, vote v  " +
            "where v.VOTE_ID = vou.VOTE_ID and v.VOTE_ID = ?3 ) temp  " +
            "on temp.mi = uom.MEETING_ID and temp.ui = u.USER_ID,  roles_of_users rou,  role r  " +
            "WHERE  r.role_id = rou.role_id  AND rou.user_id = u.user_id  AND r.role_id = ?1 ",
            nativeQuery = true)
    List<Object> findByRole(Integer role, Integer meetingId, Integer voteId);

}
