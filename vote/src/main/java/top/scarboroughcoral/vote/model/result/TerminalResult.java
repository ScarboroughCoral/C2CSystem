package top.scarboroughcoral.vote.model.result;

/**
 * Created by Hw Du on 2018/6/21.
 */
public class TerminalResult {
    String terminal_id;
    String terminal_status;
    String terminal_sequence;
    Boolean is_in_meeting;

    public TerminalResult() {
    }

    public TerminalResult(Object obj) {
        Object[] objArr = (Object[]) obj;
        this.terminal_id = (String) objArr[0];
        this.terminal_status = (String) objArr[1];
        this.terminal_sequence = (String) objArr[2];
        this.is_in_meeting = objArr[3] != null;
    }

    public TerminalResult(String terminal_id, String terminal_status, String terminal_sequence, Boolean is_in_meeting) {
        this.terminal_id = terminal_id;
        this.terminal_status = terminal_status;
        this.terminal_sequence = terminal_sequence;
        this.is_in_meeting = is_in_meeting;
    }

    public String getTerminal_sequence() {
        return terminal_sequence;
    }

    public void setTerminal_sequence(String terminal_sequence) {
        this.terminal_sequence = terminal_sequence;
    }

    public String getTerminal_id() {

        return terminal_id;
    }

    public void setTerminal_id(String terminal_id) {
        this.terminal_id = terminal_id;
    }

    public String getTerminal_status() {
        return terminal_status;
    }

    public void setTerminal_status(String terminal_status) {
        this.terminal_status = terminal_status;
    }

    public Boolean getIs_in_meeting() {
        return is_in_meeting;
    }

    public void setIs_in_meeting(Boolean is_in_meeting) {
        this.is_in_meeting = is_in_meeting;
    }
}
