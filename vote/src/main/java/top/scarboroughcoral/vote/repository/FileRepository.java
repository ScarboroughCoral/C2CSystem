package top.scarboroughcoral.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.scarboroughcoral.vote.model.entity.File;

import java.util.List;

public interface FileRepository extends JpaRepository<File,Integer> {

    @Query(value = "select * from file where meeting_id = ?1",nativeQuery = true)
    File findByMeeting_id(Integer meeting_id);
}
