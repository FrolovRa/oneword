package dao;

import entities.User;

import javax.ejb.Local;
import java.util.List;


public interface UserDao {

    User LogInUser(String username, String password);

    User getUser(int id);

    int addUser(User user);

    List searchUser(String search);

    void subscribe(User user, User subscribe);

    void unsubscribe(User user, User subscribe);

}
