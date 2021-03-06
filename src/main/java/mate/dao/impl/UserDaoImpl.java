package mate.dao.impl;

import java.util.List;
import mate.dao.UserDao;
import mate.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User add(User user) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Cant insert User entity", e);
        } finally {
            session.close();
        }
    }

    @Override
    public User get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("from User where userId = :id", User.class)
                    .setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException("Can't get User ", e);
        }
    }

    @Override
    public List<User> listUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("from User", User.class).list();
        } catch (Exception e) {
            throw new RuntimeException("Can't get User ", e);
        }
    }
}
