package email.dao;

import email.Email;

import java.util.List;

/**
 * Date: 7/10/14
 * Time: 11:55 AM
 *
 * @author Russ Forstall
 */
public interface EmailDao {
    void addEmail(Email email);

    Email getEmail(String email);

    boolean emailExists(Email email);

    void unsubscribe(Email email);

    /**
     * Gets all emails not unsubscribed.
     * @return List of Emails
     */
    List<Email> getMailingList();


}
