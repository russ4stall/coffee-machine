package email;

import java.util.Date;

/**
 * Created by russ on 8/5/14.
 */
public class LogEmail {
    private int id;
    private String email;
    private String log;
    private Date createdOn;

    public LogEmail(String email, String log, Date createdOn) {
        this.email = email;
        this.log = log;
        this.createdOn = createdOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
