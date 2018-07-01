package top.scarboroughcoral.vote.model.dto;

import top.scarboroughcoral.vote.model.entity.Table_Row_Column;

import java.util.List;

public class Table_RowDTO {

    private List<Table_Row_Column> columns;
    private Integer row_id;
    private Integer vote_table_id;
    private Integer row_order_number;

    public Table_RowDTO() {
    }

    public Table_RowDTO(List<Table_Row_Column> columns, Integer row_id, Integer vote_table_id, Integer row_order_number) {
        this.columns = columns;
        this.row_id = row_id;
        this.vote_table_id = vote_table_id;
        this.row_order_number = row_order_number;
    }

    public List<Table_Row_Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Table_Row_Column> columns) {
        this.columns = columns;
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
