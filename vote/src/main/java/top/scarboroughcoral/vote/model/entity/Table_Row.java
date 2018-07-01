package top.scarboroughcoral.vote.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "table_row")
public class Table_Row {
    @GeneratedValue
    @Id
    private Integer row_id;
    private Integer vote_table_id;
    private Integer row_order_number;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "table_row")
    private List<Table_Row_Column> table_row_columns;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vote_table_id",insertable = false,updatable = false)
    @JsonBackReference
    private Vote_Table vote_table;

    public Table_Row() {
    }

    public Table_Row(int vote_table_id, int row_num) {
        this.vote_table_id = vote_table_id;
        this.row_order_number = row_num;

    }

    public Vote_Table getVote_table() {
        return vote_table;
    }

    public void setVote_table(Vote_Table vote_table) {
        this.vote_table = vote_table;
    }

    public List<Table_Row_Column> getTable_row_columns() {
        return table_row_columns;
    }

    public void setTable_row_columns(List<Table_Row_Column> table_row_columns) {
        this.table_row_columns = table_row_columns;
    }

    public Integer getRow_id() {
        return row_id;
    }

    public void setRow_id(Integer row_id) {
        this.row_id = row_id;
    }

    public Integer getVote_table_id() {
        return vote_table_id;
    }

    public void setVote_table_id(Integer vote_table_id) {
        this.vote_table_id = vote_table_id;
    }

    public Integer getRow_order_number() {
        return row_order_number;
    }

    public void setRow_order_number(Integer row_order_number) {
        this.row_order_number = row_order_number;
    }
}
