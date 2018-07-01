package top.scarboroughcoral.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.scarboroughcoral.vote.model.entity.Terminal;

import java.util.List;

public interface TerminalRepository extends JpaRepository<Terminal,String> {
    @Query(value = "SELECT  t.* FROM  terminal t " +
            "WHERE   t.terminal_id = ?1 "
            ,nativeQuery = true)
    Terminal getTerminalByTerminalId(String terminalId);
}
