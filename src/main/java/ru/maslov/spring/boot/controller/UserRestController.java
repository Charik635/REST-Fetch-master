package ru.maslov.spring.boot.controller;

import com.google.gson.Gson;
import ru.maslov.spring.boot.model.User;
import ru.maslov.spring.boot.service.UserService;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping("/rest")
public class UserRestController {
    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    //all-users
    @GetMapping
    public String getUsers() {
        Gson gson = new Gson();
        String json = gson.toJson(userService.getAllUsers());

        return json;
    }

    //admin-edit
    @PostMapping("admin/edit")
    public User editUser(String user, String roleIds) {
        User userFromJson = getUser(user, roleIds);
        userService.update(userFromJson);

        return userFromJson;
    }

    //admin-delete
    @PostMapping("admin/delete")
    public String deleteUser(String id){
        userService.deleteUser(Integer.parseInt(id));

        return id;
    }

    //admin-add
    @PostMapping("admin/add")
    public User addUser(String user, String roleIds){
        User userFromJson = getUser(user, roleIds);
        userService.insert(userFromJson);

        return userFromJson;
    }

    //user-get
    private User getUser(String user, String roleIds) {
        Gson gson = new Gson();
        User userFromJson = gson.fromJson(user, User.class);

        List<String> roleIdsFromJson = gson.fromJson(roleIds, ArrayList.class);
        String[] roleIdsFromList = new String[roleIdsFromJson.size()];

        for (int i = 0; i < roleIdsFromList.length; i++) {
            roleIdsFromList[i] = roleIdsFromJson.get(i);
        }
        userFromJson.setRole(userService.getRoles(roleIdsFromList));

        return userFromJson;
    }
}

