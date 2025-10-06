package com.LibraryManagment.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.LibraryManagment.models.Role;
import com.LibraryManagment.models.User;

@Service
public interface UserService {
    Optional<User> findByUsername(String username);

    List<User> all();

    void register(User user);

    void add(Long userId, int roleId);

    User get(Long id);

    void remove(Long userId, int roleId);

    public Set<Role> getUserRoles(Long userId);
}
