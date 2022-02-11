package com.example.UserManagement.service;

import com.example.UserManagement.model.Role;
import com.example.UserManagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
