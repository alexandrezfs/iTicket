package iTicket.util;

import iTicket.dao.UserDao;
import iTicket.entities.UserEntity;
import iTicket.jpa.UserJpa;

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

}
