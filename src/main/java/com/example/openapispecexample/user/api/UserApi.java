package com.example.openapispecexample.user.api;

import com.example.openapispecexample.common.model.ApiResponse;
import com.example.openapispecexample.security.jwt.JwtAuthentication;
import com.example.openapispecexample.user.dto.response.MyUserInfoResponse;
import com.example.openapispecexample.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @GetMapping("/me")
    ResponseEntity<ApiResponse<MyUserInfoResponse>> myPage(@AuthenticationPrincipal JwtAuthentication user) {
        return ResponseEntity.ok().body(new ApiResponse<>(userService.findUserInfo(user.id())));
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAccount(@AuthenticationPrincipal JwtAuthentication user) {
        userService.deleteUserAccount(user.id());
    }

}
