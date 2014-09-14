package iTicket.controller;

import iTicket.entities.TicketEntity;
import iTicket.jpa.TicketJpa;
import iTicket.jpa.UserJpa;
import iTicket.template.UiBean;
import iTicket.util.StaticValues;

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

    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
    HttpSession session = request.getSession(true);

    private TicketEntity ticket = new TicketEntity();
    private TicketEntity ticketToShow = new TicketEntity();
    private List<TicketEntity> allTickets = new ArrayList<TicketEntity>();

    public TicketController() {

    }

    public String addTicket() {

        TicketJpa tJ = new TicketJpa();

        this.ticket.setCreationDate(new Timestamp(new Date().getTime()));
        this.ticket.setStatus(StaticValues.TICKET_STATUS_NEW);
        this.ticket.setUserByUserId(new UserJpa().getUserById((Integer) session.getAttribute(StaticValues.USER_ID_SESSION_ATTRIBUTE)));

        tJ.addTicket(this.ticket);

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        new UiBean().displayAddTicketFlash();

        try {
            ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void showTicket() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            ec.redirect(ec.getRequestContextPath() + "/showTicket.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }

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
