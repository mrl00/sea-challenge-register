package com.sea.challenge.register.services.security;

import com.sea.challenge.register.exceptions.security.UserNameAlreadyExistsException;
import com.sea.challenge.register.models.entities.security.User;
import com.sea.challenge.register.repositories.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository repository;

    public User saveUser(User user) {
        if(repository.existsByUserName(user.getUsername()))
            throw new UserNameAlreadyExistsException("user already exists", user.getUsername());
        return repository.save(user);
    }
}
