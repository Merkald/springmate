package dao.impl;

import dao.UserDao;
import java.util.List;
import model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User add(User user) {
        sessionFactory.openSession().save(user);
        return user;
    }

    @Override
    public List<User> listUsers() {
        return sessionFactory.openSession()
                .createQuery("from User", User.class).list();

    }
}
