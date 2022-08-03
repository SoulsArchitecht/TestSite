package ru.sshibko.testsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sshibko.testsite.model.entity.User;
import ru.sshibko.testsite.service.UserService;

import java.util.List;

@Controller
public class DefaultController {

    private final UserService userService;

    @Autowired
    public DefaultController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<User> userList =  userService.userList();
        model.addAttribute("userList", userList);
/*        if(userList != null) {
            return "index";
        }
        return "error";*/
        return "index";

    }
}
