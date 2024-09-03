package hiber.service;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();


    void deleteUsers();

    User findOwner(String model, Integer series);

    public SessionFactory getSessionFactory();
}