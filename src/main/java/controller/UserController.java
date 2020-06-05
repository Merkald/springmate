package controller;

import config.AppConfig;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.UserResponceDto;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;
import util.HashUtil;

@RestController
public class UserController {
    private static AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    private static UserService userService = context.getBean(UserService.class);

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

    @ResponseBody
    @GetMapping("/{id}")
    public UserResponceDto get(@PathVariable(name = "id") Long id) {
        User user = userService.get(id);
        UserResponceDto userDto = new UserResponceDto();
        userDto.setName(user.getFirstName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    @ResponseBody
    @GetMapping("/")
    public List<UserResponceDto> getAll() {
        List<User> list = userService.listUsers();
        List<UserResponceDto> result = new ArrayList<>();
        for (User u : list) {
            UserResponceDto userResponceDto = new UserResponceDto();
            userResponceDto.setEmail(u.getEmail());
            userResponceDto.setName(u.getFirstName());
            result.add(userResponceDto);
        }
        return result;
    }
}
