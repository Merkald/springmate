package mate.service.impl;

import java.util.List;
import mate.dao.UserDao;
import mate.model.User;
import mate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }
}
