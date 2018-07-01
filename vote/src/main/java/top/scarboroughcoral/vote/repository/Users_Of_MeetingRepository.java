package top.scarboroughcoral.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import top.scarboroughcoral.vote.model.entity.Users_Of_Meeting;

public interface Users_Of_MeetingRepository extends JpaRepository<Users_Of_Meeting,Integer> {

    @Query(value = "select * from users_of_meeting where meeting_id=?1 and user_id=?2",nativeQuery = true)
    Users_Of_Meeting existsByMeeting_idAndAndUser_id(Integer meeting_id,Integer user_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE from users_of_meeting where meeting_id=?1",nativeQuery = true)
    Integer deleteAllByMeetingId(Integer meeting_id);


}
