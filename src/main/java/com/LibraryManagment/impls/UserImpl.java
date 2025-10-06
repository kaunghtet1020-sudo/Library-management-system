package com.LibraryManagment.impls;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LibraryManagment.models.Role;
import com.LibraryManagment.models.User;
import com.LibraryManagment.repos.RoleRepo;
import com.LibraryManagment.repos.UserRepo;
import com.LibraryManagment.services.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserImpl implements UserService {
 @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final RoleRepo roleRepo;
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> all() {
        return userRepo.findAll();
    }

    @Override
    public void register(User user) {
         userRepo.save(user);
    }

    @Override
    public void add(Long userId, int roleId) {
       User user=get(userId);
       Role role =roleRepo.findById(roleId).orElseThrow(()->new RuntimeException("No role with that id"));
       if (user!=null && role!=null) {
         Set<Role>roles=user.getRoles();
         roles.add(role);
         user.setRoles(roles);
         userRepo.save(user);
       }
    }

    @Override
    public User get(Long id) {
       return userRepo.findById(id).orElseThrow(()->new RuntimeException("No user with that id"));
    }

    @Override
    public void remove(Long userId, int roleId) {
        User user=get(userId);
       Role role =roleRepo.findById(roleId).orElseThrow(()->new RuntimeException("No role with that id"));
       if (user!=null && role!=null) {
         Set<Role>roles=user.getRoles();
         roles.remove(role);
         user.setRoles(roles);
         userRepo.save(user);
       }
    }

    @Override
    public Set<Role> getUserRoles(Long userId) {
        User user=get(userId);
        return user.getRoles();
    }

}
