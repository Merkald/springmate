import config.AppConfig;
import model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.UserService;

public class Main {
    private static AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    private static UserService userService = context.getBean(UserService.class);

    public static void main(String[] args) {
        System.out.println("hi");
        User user = new User();
        user.setAge(15);
        user.setEmail("asd");
        user.setFirstName("q");
        user.setLastName("w");
        user.setLogin("l");
        user.setPassword("p");
        userService.add(user);
        user.setLogin("l1");
        user.setEmail("asd1");
        user.setPassword("p1");
        userService.add(user);
        System.out.println(userService.listUsers());
    }
}
