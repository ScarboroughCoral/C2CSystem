package top.scarboroughcoral.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.scarboroughcoral.vote.model.entity.Table_Row_Column;

import java.util.List;

public interface Table_Row_ColumnRepository extends JpaRepository<Table_Row_Column,Integer> {

//    @Query(value = "update Table_Row_Column trc set trc.column_value=?1 where trc.column_id")
//    List<Table_Row_Column>  updateResult(List<Table_Row_Column> table_row_columns);
}
