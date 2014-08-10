package coffeemachine;

import email.dao.EmailDao;
import email.dao.EmailDaoImpl;
import email.dao.LogEmailDao;
import email.dao.LogEmailDaoImpl;
import pot.dao.PotDao;
import pot.dao.PotDaoImpl;
import services.messages.MessageMaker;
import services.messages.SimpleMessageMaker;
import services.notifier.Notifier;
import services.notifier.NotifierImpl;
import util.DataSource;

/**
 * This is a class that handles all dependencies and configurations.
 *
 * @author russ
 */
public class ApplicationScope {
    private static ApplicationScope instance = null;

    private ApplicationScope() {
    }

    public static ApplicationScope getInstance() {
        if (instance == null) {
            instance = new ApplicationScope();
        }
        return instance;
    }

    //Data Source
    private DataSource dataSource = new DataSource("jdbc:mysql://localhost:3306/coffeemachine", "coffee", "coffee");

    //DAOS

    private EmailDao emailDao = new EmailDaoImpl();
    private LogEmailDao logEmailDao = new LogEmailDaoImpl();
    private PotDao potDao = new PotDaoImpl();

    //Services

    private MessageMaker messageMaker = new SimpleMessageMaker();
    private Notifier notifier = new NotifierImpl();


    //Getters

    public DataSource getDataSource() {
        return dataSource;
    }

    public EmailDao getEmailDao() {
        return emailDao;
    }

    public LogEmailDao getLogEmailDao() {
        return logEmailDao;
    }

    public PotDao getPotDao() {
        return potDao;
    }

    public MessageMaker getMessageMaker() {
        return messageMaker;
    }

    public Notifier getNotifier() {
        return notifier;
    }
}



