package top.scarboroughcoral.vote.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "table_row_column")
@DynamicUpdate
public class Table_Row_Column {
    @Id
    @GeneratedValue
    private Integer column_id;
    private Integer row_id;
    private Integer column_order_number;
    private String column_value;
    private boolean is_result;

    public Table_Row_Column() {
    }

    public Table_Row_Column(Integer row_id, String s, Integer column_order_number, Boolean is_result) {
        this.row_id = row_id;
        this.column_order_number = column_order_number;
        this.column_value = s;
        this.is_result = is_result;
    }



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "row_id",insertable = false,updatable = false)
    private Table_Row table_row;

    @JsonBackReference
    public Table_Row getTable_row() {
        return table_row;
    }


    public void setTable_row(Table_Row table_row) {
        this.table_row = table_row;
    }

    public Integer getColumn_id() {
        return column_id;
    }

    public void setColumn_id(Integer column_id) {
        this.column_id = column_id;
    }

    public Integer getRow_id() {
        return row_id;
    }

    public void setRow_id(Integer row_id) {
        this.row_id = row_id;
    }

    public Integer getColumn_order_number() {
        return column_order_number;
    }

    public void setColumn_order_number(Integer column_order_number) {
        this.column_order_number = column_order_number;
    }

    public String getColumn_value() {
        return column_value;
    }

    public void setColumn_value(String column_value) {
        this.column_value = column_value;
    }

    public boolean isIs_result() {
        return is_result;
    }

    public void setIs_result(boolean is_result) {
        this.is_result = is_result;
    }
}
