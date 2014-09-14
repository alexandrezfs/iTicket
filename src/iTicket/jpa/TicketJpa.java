package iTicket.jpa;

import iTicket.dao.TicketDao;
import iTicket.entities.TicketEntity;
import iTicket.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

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
    public List<TicketEntity> getAllTickets() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        List<TicketEntity> tickets =  session.createCriteria(TicketEntity.class).list();
        session.getTransaction().commit();

        return tickets;
    }

    @Override
    public List<TicketEntity> getTicketsByStatus(String status) {
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
