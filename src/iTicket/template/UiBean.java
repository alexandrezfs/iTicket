package iTicket.template;

import iTicket.util.StaticValues;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean
@RequestScoped
public class UiBean implements Serializable {

    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
    HttpSession session = request.getSession(true);

    public UiBean() {}

    public void displaySignupFlash() {

        session.setAttribute(StaticValues.FLASH_SESSION_SIGNUP_ID, true);

    }

    public void destroySignupFlash() {

        session.setAttribute(StaticValues.FLASH_SESSION_SIGNUP_ID, null);

    }

    public void displaySigninFlash() {

        session.setAttribute(StaticValues.FLASH_SESSION_SIGNIN_ID, true);

    }

    public void destroySigninFlash() {

        session.setAttribute(StaticValues.FLASH_SESSION_SIGNIN_ID, null);

    }

    public void displayAddTicketFlash() {

        session.setAttribute(StaticValues.FLASH_SESSION_ADDTICKET_ID, true);

    }

    public void destroyAddTicketFlash() {

        session.setAttribute(StaticValues.FLASH_SESSION_ADDTICKET_ID, null);

    }}