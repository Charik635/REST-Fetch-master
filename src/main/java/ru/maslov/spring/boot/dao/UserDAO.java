package ru.maslov.spring.boot.dao;


import ru.maslov.spring.boot.model.Role;
import ru.maslov.spring.boot.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;


public interface UserDAO {
    List<User> read();
    List<Role> readRole();
    Set<Role> getRoles(String[] ids);

    void insert(User user);
    void update(User user);
    void delete(Integer id);

    User read(Integer id);
    public UserDetails findByUsername(String username);
}