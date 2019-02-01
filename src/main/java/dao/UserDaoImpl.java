package dao;

import beans.HibernateRefactor;
import entities.Post;
import entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public User LogInUser(String username, String password) {

        try (Session session = HibernateRefactor.getSession()) {

            Query query = session.createQuery("from entities.User where username = :uname");
            query.setParameter("uname", username);
            return (User) query.uniqueResult();
        }
    }

    @Override
    public User getUser(int id) {
        try (Session session = HibernateRefactor.getSession()) {
            return (User) session.get("entities.User", id);
        }
    }

    @Override
    public int addUser(User user) {
        try (Session session = HibernateRefactor.getSession()) {
            return (int) session.save(user);
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<User> searchUser(String search) {
        try (Session session = HibernateRefactor.getSession()) {

            Query query = session.createQuery("from entities.User where username like :search");
            query.setParameter("search", search +"%");

            return query.setMaxResults(5).list();
        }
    }

    @Override
    public void subscribe(User user, User subscribe) {
        try (Session session = HibernateRefactor.getSession()) {
            session.beginTransaction();

            user.getFollowing().add(subscribe);
            session.update(user);
            session.update(subscribe);

            session.getTransaction().commit();
        }
    }

    @Override
    public void unsubscribe(User user, User subscribe) {
        try (Session session = HibernateRefactor.getSession()) {
            session.beginTransaction();

            user.getFollowing().remove(subscribe);
            session.update(user);
            session.update(subscribe);

            session.getTransaction().commit();

        }
    }
}
