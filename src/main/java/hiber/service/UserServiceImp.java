package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }


    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }


    @Transactional
    @Override
    public void deleteUsers() {
        userDao.deleteUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public User findOwner(String model, Integer series) {
        return userDao.findOwner(model, series);
    }

    @Transactional
    @Override
    public SessionFactory getSessionFactory() {
        return userDao.getSessionFactory();
    }

}
