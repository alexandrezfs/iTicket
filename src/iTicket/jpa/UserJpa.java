package iTicket.jpa;

import iTicket.dao.UserDao;
import iTicket.entities.DeveloperEntity;
import iTicket.entities.ProductOwnerEntity;
import iTicket.entities.TicketEntity;
import iTicket.entities.UserEntity;
import org.hibernate.Session;
import iTicket.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class UserJpa implements UserDao {

    @Override
    public UserEntity addUser(UserEntity user) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();

        return user;
    }

    @Override
    public DeveloperEntity addDeveloper(DeveloperEntity developer) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        session.save(developer);
        session.getTransaction().commit();

        return developer;
    }

    @Override
    public ProductOwnerEntity addProductOwner(ProductOwnerEntity productOwner) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        session.save(productOwner);
        session.getTransaction().commit();

        return productOwner;
    }

    @Override
    public UserEntity getUserByEmailAndPassword(String email, String password) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        UserEntity user = (UserEntity) session
                .createQuery("select u from UserEntity u where u.email = :email and u.password = :password")
                .setParameter("email", email)
                .setParameter("password", password)
                .uniqueResult();

        session.getTransaction().commit();

        return user;
    }

    @Override
    public UserEntity getUserByEmail(String email) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        UserEntity user = (UserEntity) session
                .createQuery("select u from UserEntity u where u.email = :email")
                .setParameter("email", email)
                .uniqueResult();

        session.getTransaction().commit();

        return user;
    }

    @Override
    public UserEntity getUserById(int user_id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        UserEntity user = (UserEntity) session.get(UserEntity.class, user_id);

        session.getTransaction().commit();

        return user;
    }

    @Override
    public List<UserEntity> getUsers() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        List<UserEntity> users = session
                .createQuery("select u from UserEntity u ORDER BY u.id ASC")
                .list();

        session.getTransaction().commit();

        return users;
    }

    @Override
    public List<DeveloperEntity> getDevelopers() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        List<DeveloperEntity> users = session
                .createQuery("select u from DeveloperEntity u ORDER BY u.id ASC")
                .list();

        session.getTransaction().commit();

        return users;
    }

    @Override
    public List<ProductOwnerEntity> getProductOwners() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        List<ProductOwnerEntity> users = session
                .createQuery("select u from ProductOwnerEntity u ORDER BY u.id ASC")
                .list();

        session.getTransaction().commit();

        return users;
    }
}
