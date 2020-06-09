package mate.service;

import java.util.List;
import mate.model.User;

public interface UserService {
    void add(User user);

    User get(Long id);

    List<User> listUsers();
}
