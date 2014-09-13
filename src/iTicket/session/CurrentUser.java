package iTicket.session;


import iTicket.entities.UserEntity;
import iTicket.jpa.UserJpa;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean
@RequestScoped
public class CurrentUser {

    private UserEntity user;

    @PostConstruct
    public void init() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession session = request.getSession(true);

        if(session.getAttribute("UserIdSession") != null) {

            int userId = (Integer) session.getAttribute("UserIdSession");

            this.user = new UserJpa().getUserById(userId);

        }
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
