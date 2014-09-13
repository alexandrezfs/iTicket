package iTicket.dao;

import iTicket.entities.TicketEntity;

import java.util.ArrayList;

public interface TicketDao {

    TicketEntity addTicket(TicketEntity ticket);
    ArrayList<TicketEntity> getAllTickets();
    ArrayList<TicketEntity> getTicketsByStatus(String status);
    void removeAllTickets();
    TicketEntity getTicketById(int ticket_id);

}
