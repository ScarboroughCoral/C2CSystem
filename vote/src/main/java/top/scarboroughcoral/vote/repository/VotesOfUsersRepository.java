package top.scarboroughcoral.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import top.scarboroughcoral.vote.model.entity.Votes_Of_Users;

/**
 * Created by Hw Du on 2018/6/21.
 */
public interface VotesOfUsersRepository extends JpaRepository<Votes_Of_Users,Integer> {

    @Modifying
    @Transactional
    @Query(value = "DELETE from votes_of_users where vote_id=?1",nativeQuery = true)
    Integer deleteAllByVoteId(Integer voteId);

    @Query(value = "SELECT * from votes_of_users where user_id = ?1 and vote_id=?2",nativeQuery = true)
    Votes_Of_Users findByUserIdAndVoteId(Integer userId, Integer voteId);
}