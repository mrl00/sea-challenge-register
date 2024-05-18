package com.sea.challenge.register.services.security;

import com.sea.challenge.register.exceptions.security.UserNameAlreadyExistsException;
import com.sea.challenge.register.mocks.UserMock;
import com.sea.challenge.register.models.entities.security.User;
import com.sea.challenge.register.repositories.security.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ActiveProfiles({"test"})
@SpringBootTest
public class AuthenticationServiceTest {

    @Autowired
    private AuthenticationService authenticationService;

    @MockBean
    private UserRepository repository;

    @Test
    public void saveUserTest_whenUserIsNotPersisted() {
        User user = UserMock.SIMPLE_USER_ADMIN_MODEL;
        when(repository.existsByUserName(anyString()))
                .thenReturn(false);

        when(repository.save(any()))
                .thenReturn(user);

        assertEquals(authenticationService.saveUser(user), user);
    }

    @Test
    public void saveUserTest_whenUserIsPersisted() {
        User user = UserMock.SIMPLE_USER_ADMIN_MODEL;
        when(repository.existsByUserName(anyString()))
                .thenReturn(true);

        assertThrows(UserNameAlreadyExistsException.class, () -> authenticationService.saveUser(user));
    }

}
