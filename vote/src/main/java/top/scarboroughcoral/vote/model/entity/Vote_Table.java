package top.scarboroughcoral.vote.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import top.scarboroughcoral.vote.model.dto.VoteTableContentDTO;
import top.scarboroughcoral.vote.model.dto.Vote_TableDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "vote_table")
public class Vote_Table {
    @Id
    @GeneratedValue
    private Integer vote_table_id;
    private Integer vote_id;
    private Integer row_number;
    private Integer column_number;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "vote_table")
    private List<Table_Row> table_rows;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vote_id",insertable = false,updatable = false)
    @JsonBackReference
    private Vote vote;

    public Vote_Table() {
    }

    public Vote_Table(Integer vote_table_id, Integer vote_id, Integer row_number, Integer column_number) {
        this.vote_table_id = vote_table_id;
        this.vote_id = vote_id;
        this.row_number = row_number;
        this.column_number = column_number;
    }

    public Vote_Table(VoteTableContentDTO table, Integer vote_id) {
        this.vote_id = vote_id;
        this.row_number = table.getRows().size();
        this.column_number = table.getHeader().size();
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public List<Table_Row> getTable_rows() {
        return table_rows;
    }

    public void setTable_rows(List<Table_Row> table_rows) {
        this.table_rows = table_rows;
    }

    public Integer getVote_table_id() {
        return vote_table_id;
    }

    public void setVote_table_id(Integer vote_table_id) {
        this.vote_table_id = vote_table_id;
    }

    public Integer getVote_id() {
        return vote_id;
    }

    public void setVote_id(Integer vote_id) {
        this.vote_id = vote_id;
    }

    public Integer getRow_number() {
        return row_number;
    }

    public void setRow_number(Integer row_number) {
        this.row_number = row_number;
    }

    public Integer getColumn_number() {
        return column_number;
    }

    public void setColumn_number(Integer column_number) {
        this.column_number = column_number;
    }
}
