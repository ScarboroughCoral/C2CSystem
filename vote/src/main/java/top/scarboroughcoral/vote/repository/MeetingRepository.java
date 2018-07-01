package top.scarboroughcoral.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.scarboroughcoral.vote.model.entity.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting,Integer> {

    @Query(value = "select * from meeting order by meeting_id desc limit 1",nativeQuery = true)
    Meeting findLatestMeeting();

    @Query(value = "select * from Meeting where meeting_id = ?1", nativeQuery = true)
    Meeting findById();
}
