package iTicket.jpa;

import iTicket.dao.UserDao;
import iTicket.entities.DeveloperEntity;
import iTicket.entities.ProductOwnerEntity;
import iTicket.entities.UserEntity;
import org.hibernate.Session;
import iTicket.util.HibernateUtil;

import java.util.ArrayList;

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
    public ArrayList<UserEntity> getUsers() {
        return null;
    }

    @Override
    public ArrayList<DeveloperEntity> getDevelopers() {
        return null;
    }

    @Override
    public ArrayList<ProductOwnerEntity> getProductOwners() {
        return null;
    }
}
