package hiber.dao;


import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private final SessionFactory sessionFactory;

    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }


    @Override
    @SuppressWarnings("unchecked")
    public void deleteUsers() {
        List<User> users = listUsers();
        for (User user : users) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }


    @Override
    public User findOwner(String carModel, int carSeries) {
        Query findCarQuery = sessionFactory.getCurrentSession().createQuery("from Car where model_car = :model and series = :series")
                .setParameter("model", carModel)
                .setParameter("series", carSeries);
        List findCarList = findCarQuery.getResultList();
        if (!findCarList.isEmpty()) {
            Car findCar = (Car) findCarList.get(0);
            List<User> ListUser = listUsers();
            User findUser = ListUser.stream()
                    .filter(user -> user.getCar().equals(findCar))
                    .findAny()
                    .orElse(null);
            return findUser;
        } else return null;
    }


//    @Override
//    public List<User> findOwner(String modelCar, Integer series) {
//        List<User> users = listUsers();
//        List<User> findOwner = new ArrayList<>();
//        if (users.isEmpty()) {
//            System.out.println("users is empty");
//        } else {
//            for (User user : users) {
//                if ((user.getCar().getModel_car().equals(modelCar)) && (user.getCar().getSeries().equals(series))) {
//                    findOwner.add(user);
//
//                }else continue;
//            }
//
//
//        }
//        return findOwner;
//    }


}
