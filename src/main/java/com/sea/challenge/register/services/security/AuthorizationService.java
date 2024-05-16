package com.sea.challenge.register.services.security;

import com.sea.challenge.register.repositories.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDetails> optionalUserDetails = userRepository.findByUserName(username);
        if(optionalUserDetails.isEmpty())
            throw new UsernameNotFoundException(String.format("% username is not found"));
        return optionalUserDetails.get();
    }
}
