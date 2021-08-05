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

    // entry point (get)
    @GetMapping(value = "login")
    public String loginGet() {
        return "login";
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

    // add (post)
    @PostMapping(value = "admin/add")
    public String adminAddPost(User user, String[] roleIds) {
        user.setRole(userService.getRoles(roleIds));
        userService.insert(user);
        return "redirect:/admin";
    }

    // edit (get)
    @GetMapping(value = "admin/edit")
    public String adminEditGet(ModelMap model, @RequestParam("id") Integer id) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        List<Role> roles = userService.getAllRoles();

        roles.forEach(role -> role.setInUser(user.isRoleInUser(role)));
        model.addAttribute("roles", roles);
        return "admin/edit";
    }

    // edit (post)
    @PostMapping(value = "admin/edit")
    public String adminEditPost(User user, String[] roleIds) {
        user.setRole(userService.getRoles(roleIds));
        userService.update(user);
        return "redirect:/admin";
    }

    // delete (post)
    @PostMapping(value = "admin/delete")
    public String adminDeletePost(@RequestParam("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    // user (get)
    @GetMapping(value = "user")
    public String userGet(ModelMap modelMap, HttpSession httpSession) {
        modelMap.addAttribute("user", httpSession.getAttribute("user"));
        return "user";
    }

}