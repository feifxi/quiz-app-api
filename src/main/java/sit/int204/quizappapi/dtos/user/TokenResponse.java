package sit.int204.quizappapi.dtos.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TokenResponse {
    @JsonIgnore
    private String accessToken;
    @JsonIgnore
    private String refreshToken;

    public TokenResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccess_token() {
        return accessToken;
    }
    public String getRefresh_token() {
        return refreshToken;
    }
}
