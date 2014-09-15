package iTicket.jpa;

import iTicket.dao.TicketDao;
import iTicket.entities.TicketEntity;
import iTicket.entities.UserEntity;
import iTicket.util.HibernateUtil;
import iTicket.util.StaticValues;
import org.hibernate.Session;
import sun.security.krb5.internal.Ticket;

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
    public List<TicketEntity> getNewTickets() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        List<TicketEntity> tickets = session
                .createQuery("select t from TicketEntity t WHERE t.status = :status ORDER BY t.creationDate ASC")
                .setParameter("status", StaticValues.TICKET_STATUS_NEW)
                .list();

        session.getTransaction().commit();

        return tickets;
    }

    @Override
    public List<TicketEntity> getAllTickets() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        List<TicketEntity> tickets = session
                .createQuery("select t from TicketEntity t ORDER BY t.creationDate ASC")
                .list();

        session.getTransaction().commit();

        return tickets;
    }

    @Override
    public List<TicketEntity> getTicketsByStatus(String status) {
        return null;
    }

    @Override
    public TicketEntity getTicketById(int ticket_id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        TicketEntity ticket = (TicketEntity) session.get(TicketEntity.class, ticket_id);

        session.getTransaction().commit();

        return ticket;
    }

    @Override
    public TicketEntity deleteTicketById(int ticket_id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        TicketEntity ticket = (TicketEntity) session.load(TicketEntity.class, ticket_id);
        HibernateUtil.getSessionFactory().getCurrentSession().delete(ticket);

        session.getTransaction().commit();

        return ticket;
    }

    @Override
    public TicketEntity changeTicketStatus(int ticket_id, String newStatus) {

        TicketEntity ticket = this.getTicketById(ticket_id);
        ticket.setStatus(newStatus);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        session.update(ticket);

        session.getTransaction().commit();

        return ticket;
    }

    @Override
    public TicketEntity editTicket(TicketEntity ticket) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        session.update(ticket);

        session.getTransaction().commit();

        return ticket;
    }

}
