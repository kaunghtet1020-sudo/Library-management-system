package com.LibraryManagment.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.LibraryManagment.models.Role;

@Service
public interface RoleService {
    void add(Role role);

    List<Role> all();

    Role get(int id);

    void update(Role role, int id);

    void drop(int id);
}
