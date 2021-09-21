package br.com.bb.dfe.integration.foregon;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ForegonOAuthResponse {
    @JsonProperty(value = "access_token")
    String accessToken;

    @JsonProperty(value = "token_type")
    String tokenType;

    @JsonProperty(value = "expires_in")
    Long expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }    
    
}
