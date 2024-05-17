package com.sea.challenge.register.mocks;

import com.sea.challenge.register.models.dtos.security.UserDTO;
import com.sea.challenge.register.models.entities.security.User;
import com.sea.challenge.register.models.enums.security.UserRole;

public class UserMock {
    public static User SIMPLE_USER_ADMIN_MODEL = getSimpleUserAdminModel();
    public static User SIMPLE_USER_DEFAULT_MODEL = getSimpleUserDefaultModel();

    public static UserDTO SIMPLE_USER_ADMIN_DTO = getSimpleUserAdminDTO();
    public static UserDTO SIMPLE_USER_DEFAULT_DTO = getSimpleUserDefaultDTO();

    private static User getSimpleUserAdminModel() {
        return new User(1L, "admin", "123qwe!@#", UserRole.ADMIN);
    }

    private static User getSimpleUserDefaultModel() {
        return new User(1L, "user", "123qwe123", UserRole.DEFAULT);
    }

    private static UserDTO getSimpleUserAdminDTO() {
        return new UserDTO("admin", "123qwe!@#", UserRole.ADMIN);
    }

    private static UserDTO getSimpleUserDefaultDTO() {
        return new UserDTO("user", "123qwe123", UserRole.DEFAULT);
    }
}
