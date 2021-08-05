package ru.maslov.spring.boot.service;


import ru.maslov.spring.boot.model.Role;
import ru.maslov.spring.boot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;


public interface UserService extends UserDetailsService {

    public List<User> getAllUsers();

    public List<Role> getAllRoles();

    public Set<Role> getRoles(String[] ids);

    public void insert(User user);


    public void update(User user);

    public User getUser(Integer id);

    public void deleteUser(Integer id);

    public List<List<String>> getUserRoles(List<Role> allRoles, User user);

}




