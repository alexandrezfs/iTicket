package iTicket.jpa;

import iTicket.dao.TicketDao;
import iTicket.entities.TicketEntity;
import iTicket.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;

public class TicketJpa implements TicketDao {

    @Override
    public TicketEntity addTicket(TicketEntity ticket) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        session.save(ticket);
        session.getTransaction().commit();

        return ticket;
    }

    @Override
    public ArrayList<TicketEntity> getAllTickets() {
        return null;
    }

    @Override
    public ArrayList<TicketEntity> getTicketsByStatus(String status) {
        return null;
    }

    @Override
    public void removeAllTickets() {

    }

    @Override
    public TicketEntity getTicketById(int ticket_id) {
        return null;
    }

}
