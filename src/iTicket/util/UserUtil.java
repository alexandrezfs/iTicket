package iTicket.util;

import iTicket.dao.UserDao;
import iTicket.entities.UserEntity;
import iTicket.jpa.UserJpa;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class UserUtil {

    public void reloadUserInSession() {

        ExternalContext eC = FacesContext.getCurrentInstance().getExternalContext();

        if(eC.getSessionMap().get(StaticValues.USER_SESSION_ATTRIBUTE) != null) {

            UserDao uJ = new UserJpa();
            UserEntity userSession = (UserEntity) eC.getSessionMap().get(StaticValues.USER_SESSION_ATTRIBUTE);
            UserEntity newUser = uJ.getUserById(userSession.getId());

            eC.getSessionMap().remove(StaticValues.USER_SESSION_ATTRIBUTE);
            eC.getSessionMap().put(StaticValues.USER_SESSION_ATTRIBUTE, newUser);

        }

    }

    public void sendMail(String serverName, int port, String from, String subject, String content, String to) {

        Email email = new SimpleEmail();
        try {
            email.setHostName(serverName);
            email.setSmtpPort(port);
            email.setFrom(from);
            email.setSubject(subject);
            email.setMsg(content);
            email.addTo(to);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

}
