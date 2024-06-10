package com.alura.ForumHub.application.dtos;

/**
 * UserWithToken
 */
public record TokenResponse(
                String accessToken,
                String refreshToken,
                String typeToken) {

        public TokenResponse(String accessToken, String refreshToken, String typeToken) {
                this.accessToken = accessToken;
                this.refreshToken = refreshToken;
                this.typeToken = typeToken;
        }
}
