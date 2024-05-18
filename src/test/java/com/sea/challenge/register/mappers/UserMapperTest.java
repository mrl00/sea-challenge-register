package com.sea.challenge.register.mappers;

import com.sea.challenge.register.mocks.UserMock;
import com.sea.challenge.register.models.dtos.security.UserDTO;
import com.sea.challenge.register.models.entities.security.User;
import com.sea.challenge.register.models.mappers.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"test"})
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    public void fromUserDTOToUserModelTest() {
        UserDTO userDTO = UserMock.SIMPLE_USER_ADMIN_DTO;
        User user = mapper.fromUserDTOToUserModel(userDTO);

        Assertions.assertEquals(userDTO.getUserName(), user.getUsername());
        Assertions.assertEquals(userDTO.getPassword(), user.getPassword());
        Assertions.assertEquals(userDTO.getRole(), user.getRole());
    }
}
