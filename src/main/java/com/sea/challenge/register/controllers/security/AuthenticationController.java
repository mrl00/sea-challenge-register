package com.sea.challenge.register.controllers.security;

import com.sea.challenge.register.models.dtos.security.AuthenticationDTO;
import com.sea.challenge.register.models.dtos.security.LoginResponseDTO;
import com.sea.challenge.register.models.dtos.security.UserDTO;
import com.sea.challenge.register.models.entities.security.User;
import com.sea.challenge.register.models.mappers.UserMapper;
import com.sea.challenge.register.services.security.AuthenticationService;
import com.sea.challenge.register.services.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDTO request) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(request.getPassword());
        User user = mapper.fromUserDTOToUserModel(request);
        user.setPassword(encryptedPassword);

        authenticationService.saveUser(user);

        return ResponseEntity.ok().build();
    }
}
