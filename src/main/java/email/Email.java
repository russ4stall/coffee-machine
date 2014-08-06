package email;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.Date;

/**
 * @author Russ Forstall
 */
public class Email {
    private int id;
    private String emailAddress;
    private Date createdOn;

    public Email() {
    }

    public Email(String emailAddress) {
        this.emailAddress = emailAddress;
        this.createdOn = new Date();
    }

    public Email(int id, String emailAddress, Date createdOn) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.createdOn = createdOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public boolean setEmailAddress(String emailAddress) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        if (emailValidator.isValid(emailAddress)) {
            this.emailAddress = emailAddress;
            createdOn = new Date();
            return true;
        } else {
            return false;
        }
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
