package iTicket.jpa;

import iTicket.dao.CommentDao;
import iTicket.entities.CommentEntity;
import iTicket.util.HibernateUtil;
import org.hibernate.Session;

public class CommentJpa implements CommentDao {

    @Override
    public CommentEntity addComment(CommentEntity comment) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        session.save(comment);
        session.getTransaction().commit();

        return comment;
    }
}
