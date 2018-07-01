package top.scarboroughcoral.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import top.scarboroughcoral.vote.model.entity.Terminals_Of_Meeting;
import top.scarboroughcoral.vote.model.entity.Users_Of_Meeting;

import java.util.List;

/**
 * Created by Hw Du on 2018/6/21.
 */
public interface TerminalOfMeetingRepository extends JpaRepository<Terminals_Of_Meeting,Integer> {

    @Query(value = "SELECT  t.*, tom.t_terminal_id FROM  terminal t " +
            "LEFT JOIN (  SELECT   terminal_id t_terminal_id FROM   terminals_of_meeting  " +
            "WHERE   meeting_id = ?1 ) tom ON t.terminal_id = tom.t_terminal_id"
            ,nativeQuery = true)
    List<Object> findTerminalStatusByMeetingId(Integer meeting_id);

    @Query(value = "SELECT tom.* FROM terminals_of_meeting tom " +
            "WHERE meeting_id = ?1 and terminal_id = ?2 "
            ,nativeQuery = true)
    Terminals_Of_Meeting findTerminalByMeetingIdAndTerminalId(Integer meeting_id,String terminalId);

    @Modifying
    @Transactional
    @Query(value = "DELETE from terminals_of_meeting where meeting_id=?1",nativeQuery = true)
    Integer deleteAllByMeetingId(Integer meeting_id);
}
