package com.app.webservice.service.master;

import com.app.webservice.model.master.User;
import com.app.webservice.repo.master.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public boolean isUserExist(String username) {
        return userRepo.isExistByUsername(username);
    }

    public boolean isEmailExist(String email) {
        return userRepo.isExistByEmail(email);
    }

    public void save(User user) {
        userRepo.save(user);
    }
}
