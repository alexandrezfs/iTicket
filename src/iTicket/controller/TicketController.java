package iTicket.controller;

import iTicket.entities.CommentEntity;
import iTicket.entities.TicketEntity;
import iTicket.entities.UserEntity;
import iTicket.jpa.CommentJpa;
import iTicket.jpa.TicketJpa;
import iTicket.jpa.UserJpa;
import iTicket.template.UiBean;
import iTicket.util.StaticValues;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class TicketController implements Serializable {

    private TicketEntity ticket = new TicketEntity();
    private TicketEntity ticketToShow;
    private TicketEntity ticketToEdit;
    private List<TicketEntity> allTickets = new ArrayList<TicketEntity>();
    private CommentEntity commentToAdd = new CommentEntity();

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

    public String addTicket() {

        ExternalContext eC = FacesContext.getCurrentInstance().getExternalContext();

        TicketJpa tJ = new TicketJpa();

        this.ticket.setCreationDate(new Timestamp(new Date().getTime()));
        this.ticket.setStatus(StaticValues.TICKET_STATUS_NEW);
        this.ticket.setUserByUserId((UserEntity) eC.getSessionMap().get(StaticValues.USER_SESSION_ATTRIBUTE));

        tJ.addTicket(this.ticket);

        new UiBean().displayAddTicketFlash();

        try {
            eC.redirect(eC.getRequestContextPath() + "/index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void showTicket(int ticket_id) {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            ec.redirect(ec.getRequestContextPath() + "/showTicket.xhtml?ticket_id=" + ticket_id);
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

        new TicketJpa().changeTicketStatus(this.ticketToShow.getId(), status);

        this.showTicket(this.ticketToShow.getId());

    }

    public void goToEditTicket() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            ec.redirect(ec.getRequestContextPath() + "/editTicket.xhtml?ticket_id=" + this.ticketToShow.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void editTicket() {

        new TicketJpa().editTicket(this.ticketToEdit);

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTicket() {

        new TicketJpa().deleteTicketById(this.ticketToShow.getId());

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void commentTicket() {

        ExternalContext eC = FacesContext.getCurrentInstance().getExternalContext();

        this.commentToAdd.setTicketByTicketId(this.ticketToShow);
        this.commentToAdd.setCreationDate(new Timestamp(new Date().getTime()));
        this.commentToAdd.setUserByUserId((UserEntity) eC.getSessionMap().get(StaticValues.USER_SESSION_ATTRIBUTE));

        new CommentJpa().addComment(this.commentToAdd);

        this.showTicket(this.ticketToShow.getId());
    }

    public TicketEntity getTicket() {
        return ticket;
    }

    public void setTicket(TicketEntity ticket) {
        this.ticket = ticket;
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
}
