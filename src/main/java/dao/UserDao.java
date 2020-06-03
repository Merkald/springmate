package dao;

import java.util.List;
import model.User;

public interface UserDao {
    User add(User user);

    List<User> listUsers();
}
