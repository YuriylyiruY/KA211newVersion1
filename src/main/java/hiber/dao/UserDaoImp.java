package hiber.dao;



import hiber.model.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {


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
        User user = null;
        try  {
            String HQL = "from User u left join fetch u.car car  where car.model_car=:model and car.series=:series";


           Query query = sessionFactory.getCurrentSession().createQuery(HQL);
           query.setParameter("model", carModel);
           query.setParameter("series", carSeries);
           user = (User) query.uniqueResult();


            System.out.println(user);
            System.out.println(user.getFirstName());

        } catch (HibernateException e) {
            System.out.println("User not found");
        }
        return user;

    }
}




