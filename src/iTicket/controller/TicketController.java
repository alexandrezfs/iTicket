package iTicket.controller;

import iTicket.entities.*;
import iTicket.jpa.CommentJpa;
import iTicket.jpa.TicketJpa;
import iTicket.jpa.UserJpa;
import iTicket.util.StaticValues;
import iTicket.util.UserUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@ManagedBean
@ViewScoped
public class TicketController implements Serializable {

    private TicketEntity ticketToAdd = new TicketEntity();
    private TicketEntity ticketToShow;
    private TicketEntity ticketToEdit;
    private List<TicketEntity> allTickets = new ArrayList<TicketEntity>();
    private List<TicketEntity> newTickets = new ArrayList<TicketEntity>();
    private CommentEntity commentToAdd = new CommentEntity();
    private int assignedDeveloperId = 1;

    public TicketController() {

    }

    @PostConstruct
    public void init() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        if(request.getParameter("ticket_id") != null) {

            this.ticketToShow = new TicketJpa().getTicketById(Integer.parseInt(request.getParameter("ticket_id")));
            this.ticketToEdit = new TicketJpa().getTicketById(Integer.parseInt(request.getParameter("ticket_id")));

        }

    }

    public void addTicket() {

        ExternalContext eC = FacesContext.getCurrentInstance().getExternalContext();

        if (eC.getSessionMap().get(StaticValues.USER_TYPE_SESSION_ATTRIBUTE).equals("PRODUCT_OWNER")) {

            TicketJpa tJ = new TicketJpa();
            UserJpa uJ = new UserJpa();

            if (eC.getSessionMap().get(StaticValues.USER_TYPE_SESSION_ATTRIBUTE).equals("PRODUCT_OWNER")) {

                this.ticketToAdd.setUserByProductOwnerId((ProductOwnerEntity) eC.getSessionMap().get(StaticValues.USER_SESSION_ATTRIBUTE));
                this.ticketToAdd.setUserByDeveloperId((uJ.getUserById(this.assignedDeveloperId)));
                this.ticketToAdd.setCreationDate(new Timestamp(new Date().getTime()));

                if(this.ticketToAdd.getUserByDeveloperId().getId() == 1) {
                    this.ticketToAdd.setStatus(StaticValues.TICKET_STATUS_NEW);
                }
                else {
                    this.ticketToAdd.setStatus(StaticValues.TICKET_STATUS_IN_PROGRESS);
                }

                tJ.addTicket(this.ticketToAdd);

                new UserUtil().reloadUserInSession();

                try {
                    eC.redirect(eC.getRequestContextPath() + "/secured/myOwnedTickets.xhtml");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

    }

    public void showTicket(int ticket_id) {

        ExternalContext eC = FacesContext.getCurrentInstance().getExternalContext();

        try {
            eC.redirect(eC.getRequestContextPath() + "/secured/showTicket.xhtml?ticket_id=" + ticket_id);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void ticketToInProgress() {

        this.changeTicketStatus(StaticValues.TICKET_STATUS_IN_PROGRESS);

    }

    public void ticketToDone() {

        this.changeTicketStatus(StaticValues.TICKET_STATUS_DONE);

    }

    public void changeTicketStatus(String status) {

        ExternalContext eC = FacesContext.getCurrentInstance().getExternalContext();

        if (eC.getSessionMap().get(StaticValues.USER_TYPE_SESSION_ATTRIBUTE).equals("DEVELOPER")) {

            TicketJpa ticketJpa = new TicketJpa();

            ticketJpa.changeTicketStatus(this.ticketToShow.getId(), status);
            ticketJpa.changeTicketDeveloper(this.ticketToShow.getId(),
                    (DeveloperEntity) eC.getSessionMap().get(StaticValues.USER_SESSION_ATTRIBUTE));

            new UserUtil().reloadUserInSession();

            this.showTicket(this.ticketToShow.getId());

        }

    }

    public void goToEditTicket() {

        ExternalContext eC = FacesContext.getCurrentInstance().getExternalContext();

        try {
            eC.redirect(eC.getRequestContextPath() + "/secured/editTicket.xhtml?ticket_id=" + this.ticketToShow.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void editTicket() {

        new TicketJpa().editTicket(this.ticketToEdit);

        ExternalContext eC = FacesContext.getCurrentInstance().getExternalContext();

        try {
            eC.redirect(eC.getRequestContextPath() + "/secured/allTickets.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTicket() {

        new TicketJpa().deleteTicketById(this.ticketToShow.getId());

        new UserUtil().reloadUserInSession();

        ExternalContext eC = FacesContext.getCurrentInstance().getExternalContext();

        try {
            eC.redirect(eC.getRequestContextPath() + "/secured/allTickets.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void postComment() {

        ExternalContext eC = FacesContext.getCurrentInstance().getExternalContext();

        this.commentToAdd.setTicketByTicketId(this.ticketToShow);
        this.commentToAdd.setCreationDate(new Timestamp(new Date().getTime()));
        this.commentToAdd.setUserByUserId((UserEntity) eC.getSessionMap().get(StaticValues.USER_SESSION_ATTRIBUTE));

        new CommentJpa().addComment(this.commentToAdd);

        //send a mail to the ticket owner
        //You must configure a web server before to use it
        new UserUtil().sendMail("localhost",
                25,
                "admin@localhost",
                "New comment on ticket #" + this.ticketToShow.getId(),
                this.commentToAdd.getContent(),
                this.ticketToShow.getUserByProductOwnerId().getEmail());

        this.showTicket(this.ticketToShow.getId());
    }

    public TicketEntity getTicketToAdd() {
        return ticketToAdd;
    }

    public void setTicketToAdd(TicketEntity ticket) {
        this.ticketToAdd = ticket;
    }

    public List<TicketEntity> getAllTickets() {

        this.allTickets = new TicketJpa().getAllTickets();

        return allTickets;
    }

    public TicketEntity getTicketToEdit() {
        return ticketToEdit;
    }

    public void setTicketToEdit(TicketEntity ticketToEdit) {
        this.ticketToEdit = ticketToEdit;
    }

    public void setAllTickets(List<TicketEntity> allTickets) {
        this.allTickets = allTickets;
    }

    public TicketEntity getTicketToShow() {
        return ticketToShow;
    }

    public void setTicketToShow(TicketEntity ticketToShow) {
        this.ticketToShow = ticketToShow;
    }

    public List<TicketEntity> getNewTickets() {

        this.newTickets = new TicketJpa().getNewTickets();

        return newTickets;
    }

    public void setNewTickets(List<TicketEntity> newTickets) {
        this.newTickets = newTickets;
    }

    public CommentEntity getCommentToAdd() {
        return commentToAdd;
    }

    public void setCommentToAdd(CommentEntity commentToAdd) {
        this.commentToAdd = commentToAdd;
    }

    public int getAssignedDeveloperId() {
        return assignedDeveloperId;
    }

    public void setAssignedDeveloperId(int assignedDeveloperId) {
        this.assignedDeveloperId = assignedDeveloperId;
    }

}
