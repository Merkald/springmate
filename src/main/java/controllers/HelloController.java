package controllers;

import model.UserResponceDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @ResponseBody
    @GetMapping("/userdto")
    public UserResponceDto getUser() {
        UserResponceDto user = new UserResponceDto();
        user.setEmail("email1");
        user.setName("Nick");
        return user;
    }
}
