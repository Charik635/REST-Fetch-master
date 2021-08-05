package ru.maslov.spring.boot.controller;


import ru.maslov.spring.boot.model.Role;
import ru.maslov.spring.boot.model.User;
import ru.maslov.spring.boot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class UserController extends HttpServlet {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    // admin (get)
    @GetMapping(value = "admin")
    public String adminGet(ModelMap model, HttpSession httpSession) {
        model.addAttribute("user", httpSession.getAttribute("user"));

        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);

        List<Role> allRoles = userService.getAllRoles();
        model.addAttribute("roles", allRoles);

        Map<User, List<List<String>>> usersWithRoles = new HashMap<>();
        allUsers.forEach(user -> usersWithRoles.put(user, userService.getUserRoles(allRoles, user)));
        model.addAttribute("usersWithRoles", usersWithRoles);
        return "admin";
    }

    // user (get)
    @GetMapping(value = "user")
    public String userGet(ModelMap modelMap, HttpSession httpSession) {
        modelMap.addAttribute("user", httpSession.getAttribute("user"));
        return "user";
    }

}