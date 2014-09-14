package iTicket.dao;

import iTicket.entities.TicketEntity;

import java.util.ArrayList;
import java.util.List;

public interface TicketDao {

    TicketEntity addTicket(TicketEntity ticket);
    List<TicketEntity> getAllTickets();
    List<TicketEntity> getTicketsByStatus(String status);
    void removeAllTickets();
    TicketEntity getTicketById(int ticket_id);

}
