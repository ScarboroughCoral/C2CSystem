package top.scarboroughcoral.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.scarboroughcoral.vote.model.entity.File;
import top.scarboroughcoral.vote.model.entity.Roles_Of_Users;

import java.util.List;

public interface Roles_Of_UsersRepository extends JpaRepository<Roles_Of_Users,Integer> {

}
