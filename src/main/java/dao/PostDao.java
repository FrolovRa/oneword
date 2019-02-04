package dao;

import entities.Post;
import entities.User;

public interface PostDao {

    void addPost(Post post);

    Post getPost(int id);

    void like(User user, Post post);

    void removeLike(User user, Post post);

}
