package top.scarboroughcoral.vote.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TERMINAL")
public class Terminal {
    @Id
    private String terminal_id;
    private String terminal_status;
    private String terminal_sequence;

    public Terminal() {
    }

    public Terminal(String terminal_id, String terminalSequence) {
        this.terminal_id = terminal_id;
        this.terminal_sequence = terminalSequence;
        this.terminal_status = "离线";
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

    public String getTerminal_sequence() {
        return terminal_sequence;
    }

    public void setTerminal_sequence(String terminal_sequence) {
        this.terminal_sequence = terminal_sequence;
    }
}
