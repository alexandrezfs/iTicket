package iTicket.controller;

import iTicket.dao.UserDao;
import iTicket.entities.DeveloperEntity;
import iTicket.entities.ProductOwnerEntity;
import iTicket.entities.UserEntity;
import iTicket.jpa.UserJpa;
import iTicket.template.UiBean;
import iTicket.util.StaticValues;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.faces.application.ConfigurableNavigationHandler;

@ManagedBean
@RequestScoped
public class UserController implements Serializable {

    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
    HttpSession session = request.getSession(true);

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirthString;
    private String userType;

    public String login() {

        session = request.getSession(true);

        UserDao uJ = new UserJpa();
        UserEntity user = uJ.getUserByEmailAndPassword(this.email, this.password);

        if(user == null) {

            context.addMessage("signin-form", new FacesMessage("Authentication failed."));
        }
        else {

            session.setAttribute(StaticValues.USER_ID_SESSION_ATTRIBUTE, user.getId());
            new UiBean().displaySigninFlash();

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

            try {
                ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public String signup() {

        Timestamp dateOfBirthTimestamp = new Timestamp(0);

        try {

            dateOfBirthTimestamp = new Timestamp(new SimpleDateFormat("yyyy-MM-dd")
                    .parse(this.dateOfBirthString)
                    .getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        UserJpa uJ = new UserJpa();

        if(uJ.getUserByEmail(this.email) != null) {

            context.addMessage("signup-form", new FacesMessage("This e-mail address already exists !"));

        }
        else {

            if(this.userType.equals("DEVELOPER")){

                uJ.addDeveloper(new DeveloperEntity(
                        dateOfBirthTimestamp,
                        this.email,
                        this.lastName,
                        this.firstName,
                        this.password,
                        this.username
                ));

            }
            else if(this.userType.equals("PRODUCT_OWNER")){

                uJ.addProductOwner(new ProductOwnerEntity(
                        dateOfBirthTimestamp,
                        this.email,
                        this.lastName,
                        this.firstName,
                        this.password,
                        this.username
                ));

            }
            else {

                uJ.addUser(new UserEntity(
                        dateOfBirthTimestamp,
                        this.email,
                        this.lastName,
                        this.firstName,
                        this.password,
                        this.username
                ));

            }

            new UiBean().displaySignupFlash();

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

            try {
                ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirthString() {
        return dateOfBirthString;
    }

    public void setDateOfBirthString(String dateOfBirthString) {
        this.dateOfBirthString = dateOfBirthString;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
