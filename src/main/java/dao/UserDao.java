package dao;

import java.util.List;
import model.User;

public interface UserDao {
    User add(User user);

    User get(Long id);

    List<User> listUsers();
}
