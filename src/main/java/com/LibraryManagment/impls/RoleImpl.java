package com.LibraryManagment.impls;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LibraryManagment.models.Role;
import com.LibraryManagment.repos.RoleRepo;
import com.LibraryManagment.services.RoleService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleImpl implements RoleService {
        @Autowired
    private final RoleRepo roleRepo;

    @Override
    public void add(Role role) {
        roleRepo.save(role);
    }

    @Override
    public List<Role> all() {
        return roleRepo.findAll();
    }

    @Override
    public Role get(int id) {
        return roleRepo.findById(id).orElseThrow(() -> new RuntimeException("No role with that id"));
    }

    @Override
    public void update(Role role, int id) {
        Role dbRole = get(id);
        if (dbRole != null) {
            dbRole.setName(role.getName());
            roleRepo.save(dbRole);

        }
    }

    @Override
    public void drop(int id) {
        Role dbRole = get(id);
        if (dbRole != null) {
            roleRepo.deleteById(id);
        }
    }
}
