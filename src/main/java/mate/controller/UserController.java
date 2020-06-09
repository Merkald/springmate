package mate.controller;

import java.util.ArrayList;
import java.util.List;
import mate.model.User;
import mate.model.UserResponceDto;
import mate.service.UserService;
import mate.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/inject")
    public void injectData() {
        for (int i = 0; i < 4; i++) {
            User user = new User();
            user.setEmail("email_" + i);
            user.setSalt(HashUtil.getSalt());
            user.setPassword(HashUtil.hashPassword("password_" + i, user.getSalt()));
            user.setLogin("login_" + i);
            user.setFirstName("Name_" + i);
            user.setLastName("LName_" + i);
            user.setAge(i * 10);
            userService.add(user);
        }
    }

    @GetMapping("/{id}")
    public UserResponceDto get(@PathVariable(name = "id") Long id) {
        return tranferUserToUserResponceDto(userService.get(id));
    }

    @GetMapping
    public List<UserResponceDto> getAll() {
        List<User> list = userService.listUsers();
        List<UserResponceDto> result = new ArrayList<>();
        for (User u : list) {
            result.add(tranferUserToUserResponceDto(u));
        }
        return result;
    }

    private UserResponceDto tranferUserToUserResponceDto(User user) {
        UserResponceDto userDto = new UserResponceDto();
        userDto.setName(user.getFirstName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
