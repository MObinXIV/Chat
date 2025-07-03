package com.mobi.whatsappcloneapi.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public List<UserResponse> getAllUsersExceptSelf(Authentication connectedUser) {
        return userService.getAllUsersExceptSelf(connectedUser);
    }
}
