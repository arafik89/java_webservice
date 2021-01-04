package com.app.webservice.service.master;

import com.app.webservice.model.master.ERole;
import com.app.webservice.model.master.Role;
import com.app.webservice.repo.master.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public Optional<Role> findByName(ERole name) {
        return roleRepo.findByName(name);
    }
}
