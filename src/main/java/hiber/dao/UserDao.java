package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;

import java.util.List;

public interface UserDao {
    void add(User user);

    List<User> listUsers();


    void deleteUsers();

    User findOwner(String model, int series);

    SessionFactory getSessionFactory();
}
