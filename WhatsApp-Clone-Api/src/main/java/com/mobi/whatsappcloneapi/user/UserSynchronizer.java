package com.mobi.whatsappcloneapi.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserSynchronizer {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserSynchronizer(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void synchronizeWithIdp(Jwt token) {
        log.info("Synchronizing user with idp");
        getUserEmail(token).ifPresent(userEmail->{
            log.info("Synchronizing user having email: {}", userEmail);
//            Optional<User> optUser= userRepository.findByEmail(userEmail);
            User user = userMapper.fromTokenAttributes(token.getClaims());
//            optUser.ifPresent(value->user.setId(optUser.get().getId()));

            userRepository.save(user);
        });


    }

    private Optional<String> getUserEmail(Jwt token) {
        Map<String, Object> attributes = token.getClaims();
        if (attributes.containsKey("email")) {
            return Optional.of((String) attributes.get("email"));
        }
        return Optional.empty();
    }
}
