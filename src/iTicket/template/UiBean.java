package iTicket.template;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean
@RequestScoped
public class UiBean implements Serializable {

    public static String FLASH_SESSION_ID = "signupFlash";

    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
    HttpSession session = request.getSession(true);

    public UiBean() {}

    public void displaySignupFlash() {

        session.setAttribute(FLASH_SESSION_ID, true);

    }

    public void destroySignupFlash() {

        session.setAttribute(FLASH_SESSION_ID, null);

    }

}