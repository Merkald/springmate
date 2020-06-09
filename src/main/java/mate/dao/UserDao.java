package mate.dao;

import java.util.List;
import mate.model.User;

public interface UserDao {
    User add(User user);

    User get(Long id);

    List<User> listUsers();
}
