package top.scarboroughcoral.vote.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.annotation.Lazy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class File {

    @Id
    private Integer meeting_id;
    private String dir_url;
    private String zip_md5;

    public File() {
    }

    public File(Integer meeting_id, String dir_url,String zip_md5) {
        this.meeting_id = meeting_id;
        this.dir_url = dir_url;
        this.zip_md5 = zip_md5;
    }

    public String getZip_md5() {
        return zip_md5;
    }

    public void setZip_md5(String zip_md5) {
        this.zip_md5 = zip_md5;
    }

    public Integer getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(Integer meeting_id) {
        this.meeting_id = meeting_id;
    }

    public String getDir_url() {
        return dir_url;
    }

    public void setDir_url(String dir_url) {
        this.dir_url = dir_url;
    }
}
