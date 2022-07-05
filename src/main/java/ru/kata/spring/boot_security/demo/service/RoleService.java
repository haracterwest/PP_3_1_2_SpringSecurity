package ru.kata.spring.boot_security.demo.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class RoleService {

    @PersistenceContext
    private EntityManager entityManager;

    public RoleService() {
    }

    public Set<Role> getRoleSet() {
        try {
            return new HashSet<>(entityManager.createQuery("SELECT r FROM Role r", Role.class)
                    .getResultList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
