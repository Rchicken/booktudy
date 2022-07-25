package com.example.openapispecexample.security.oauth;

import com.example.openapispecexample.token.dto.TokenResponse;
import com.example.openapispecexample.token.service.TokenService;
import com.example.openapispecexample.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OAuthService {

    private final TokenService tokenService;
    private final UserService userService;

    @Transactional
    public TokenResponse register(UserProfile userProfile) {
        var registeredUser = userService.register(userProfile);
        return tokenService.createToken(registeredUser);
    }

}
