package com.example.openapispecexample.security.oauth;

import com.example.openapispecexample.common.vo.Email;
import com.example.openapispecexample.user.entity.User;
import com.example.openapispecexample.user.entity.UserRole;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserProfile {
    private final String oauthId;
    private final String provider;
    private final String name;
    private final String email;
    private final String profileImgUrl;

    @Builder
    public UserProfile(String oauthId, String provider, String name, String email, String profileImgUrl) {
        this.oauthId = oauthId;
        this.provider = provider;
        this.name = name;
        this.email = email;
        this.profileImgUrl = profileImgUrl;
    }

    public User toUser() {
        return User.builder()
            .oauthId(oauthId)
            .provider(provider)
            .name(name)
            .email(new Email(email))
            .profileImgUrl(profileImgUrl)
            .userRole(UserRole.GUEST)
            .build();
    }
}
