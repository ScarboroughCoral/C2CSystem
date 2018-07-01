package top.scarboroughcoral.vote.model.dto;

import java.util.List;
import java.util.Map;

/**
 * Created by Hw Du on 2018/6/20.
 */
public class VoteTableContentDTO {
    private List<String> header;
    private List<Map<String,String>> rows;

    public VoteTableContentDTO() {
    }

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public List<Map<String, String>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, String>> rows) {
        this.rows = rows;
    }
}
