package ru.maslov.spring.boot.dao;


import ru.maslov.spring.boot.model.Role;
import ru.maslov.spring.boot.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// убрал коммент
@Repository
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> read() {
        TypedQuery<User> query = em.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> readRole() {
        TypedQuery<Role> query = em.createQuery("from Role", Role.class);
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<Role> getRoles(String[] ids) {
        TypedQuery<Role> query = em.createQuery("from Role where id = :id", Role.class);
        Set<Role> roles = new HashSet<>();

        Arrays.stream(ids).forEach(roleId ->
        {query.setParameter("id", Integer.parseInt(roleId)); roles.add(query.getSingleResult());});

        return roles;
    }

    @Override
    public void insert(User user) {
        em.persist(user);
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public User read(Integer id) {
        TypedQuery<User> query = em.createQuery("from User where id = :id", User.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    public void delete(Integer id) {
        em.remove(read(id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public UserDetails findByUsername(String login) {
        TypedQuery<User> query = em.createQuery("from User where login = :login", User.class);
        query.setParameter("login", login);
        User singleResult = query.getSingleResult();
        return singleResult;
    }

}
