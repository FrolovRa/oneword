package dao;

import app.HibernateRefactor;
import entities.Post;
import entities.User;
import org.hibernate.Session;


public class PostDaoImpl implements PostDao {

    @Override
    public void addPost(Post post) {

        try (Session session = HibernateRefactor.getSession()) {

            session.save(post);



        }
    }

    @Override
    public void removePost(Post post) {
        try (Session session = HibernateRefactor.getSession()) {
            session.beginTransaction();

            session.remove(post);
            session.getTransaction().commit();
        }
    }

    @Override
    public Post getPost(int id) {

        try (Session session = HibernateRefactor.getSession()) {

            return (Post) session.get("entities.Post", id);

        }
    }

    @Override
    public void like(User user, Post post) {

        try (Session session = HibernateRefactor.getSession()) {

            session.beginTransaction();

            post.getLiked().add(user);
            session.update(post);
            session.update(user);

            session.getTransaction().commit();

        }
    }

    @Override
    public void removeLike(User user, Post post) {

        try (Session session = HibernateRefactor.getSession()) {

            session.beginTransaction();

            post.getLiked().remove(user);
            session.update(post);
            session.update(user);

            session.getTransaction().commit();

        }
    }
}
