package ru.sshibko.testsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.sshibko.testsite.model.entity.User;
import ru.sshibko.testsite.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping("/new")
    public String showNewUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "new-user";
    }

    @RequestMapping(value = "api/user/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);

        return "redirect:/";
    }

    @RequestMapping("/edit/{login}")
    public ModelAndView showEditForm(@PathVariable(name = "login") String login) {
        ModelAndView modelAndView = new ModelAndView("edit-user");

        User user = userService.getByLogin(login);
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping("/delete/{login}")
    public String deleteUser(@PathVariable(name = "login") String login) {
        userService.deleteByLogin(login);

        return  "redirect:/";
    }

    @RequestMapping("/search")
    public ModelAndView searchUser (@RequestParam String keyword) {
        List<User> searchResult = userService.search(keyword);
        ModelAndView modelAndView = new ModelAndView("search-user");
        modelAndView.addObject("searchResult", searchResult);

        return modelAndView;
    }
}
