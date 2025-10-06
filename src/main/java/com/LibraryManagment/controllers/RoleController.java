package com.LibraryManagment.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LibraryManagment.models.Role;
import com.LibraryManagment.services.RoleService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/library")
@AllArgsConstructor
public class RoleController {
    @Autowired
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<?> createRole( @RequestBody Role role) {
        Role drole = new Role();
        drole.setName(role.getName());
        roleService.add(drole);
        return ResponseEntity.ok(drole);
    }

    @GetMapping
    public ResponseEntity<List<Role>> roles() {
        List<Role> roles = roleService.all();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("{id}")
    public ResponseEntity<Role> get(@PathVariable int id) {
        return ResponseEntity.ok(roleService.get(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRole(@PathVariable int id, String name) {
        Role role = new Role();
        role.setName(name);
        roleService.update(role, id);
        return ResponseEntity.ok(role);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable int id) {
        roleService.drop(id);
        return ResponseEntity.ok("delete successfully");
    }
}
