package com.example.openapispecexample.user.service;

import com.example.openapispecexample.security.oauth.UserProfile;
import com.example.openapispecexample.user.dto.response.MyUserInfoResponse;
import com.example.openapispecexample.user.dto.response.UserRegisterResponse;
import com.example.openapispecexample.user.exception.UserNotFoundException;
import com.example.openapispecexample.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserRegisterResponse register(UserProfile userProfile) {
        var user = userRepository.findByOauthId(String.valueOf(userProfile.getOauthId()))
            .orElseGet(() -> userRepository.save(userProfile.toUser()));
        return new UserRegisterResponse(user.getId(), user.getUserRole().getGrantedAuthority());
    }

    public MyUserInfoResponse findUserInfo(Long userId) {
        var findUser = userRepository.findById(userId)
            .orElseThrow(UserNotFoundException::new);
        return MyUserInfoResponse.from(findUser);
    }

    @Transactional
    public void deleteUserAccount(Long userId) {
        userRepository.deleteById(userId);
    }

}
