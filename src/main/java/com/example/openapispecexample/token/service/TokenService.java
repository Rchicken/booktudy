package com.example.openapispecexample.token.service;

import com.example.openapispecexample.security.jwt.JwtTokenProvider;
import com.example.openapispecexample.security.jwt.exception.InvalidTokenException;
import com.example.openapispecexample.token.dto.AccessTokenResponse;
import com.example.openapispecexample.token.dto.RefreshTokenRequest;
import com.example.openapispecexample.token.dto.TokenResponse;
import com.example.openapispecexample.token.entity.Token;
import com.example.openapispecexample.token.repository.TokenRepository;
import com.example.openapispecexample.user.dto.response.UserRegisterResponse;
import com.example.openapispecexample.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TokenService {

    private final TokenRepository tokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse createToken(UserRegisterResponse user) {
        var accessToken = jwtTokenProvider.createAccessToken(user.id(), user.role());
        var refreshToken = jwtTokenProvider.createRefreshToken();
        tokenRepository.save(new Token(refreshToken, user.id()));
        return new TokenResponse(accessToken, refreshToken);
    }

    @Transactional
    public AccessTokenResponse refreshAccessToken(String accessToken, RefreshTokenRequest refreshTokenRequest) {
        jwtTokenProvider.validateAccessToken(accessToken);

        var refreshToken = refreshTokenRequest.getRefreshToken();
        jwtTokenProvider.validateToken(refreshToken);

        Claims claims = jwtTokenProvider.getClaims(accessToken);
        var userId = claims.get("userId", Long.class);
        var findRefreshToken = tokenRepository.findTokenByUserId(userId)
            .map(Token::getRefreshToken)
            .orElseThrow(InvalidTokenException::new);

        if (!refreshToken.equals(findRefreshToken)) {
            throw new InvalidTokenException();
        }

        var role = claims.get("role", String.class);
        var newAccessToken = jwtTokenProvider.createAccessToken(userId, role);
        return new AccessTokenResponse(newAccessToken);
    }

    @Transactional
    public void deleteTokenByUserId(Long userId) {
        if (!tokenRepository.existsByUserId(userId)) {
            throw new UserNotFoundException();
        }
        tokenRepository.deleteByUserId(userId);
    }
}
