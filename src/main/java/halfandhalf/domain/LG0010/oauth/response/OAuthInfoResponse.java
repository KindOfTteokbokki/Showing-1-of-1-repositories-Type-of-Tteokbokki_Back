package halfandhalf.domain.LG0010.oauth.response;

import halfandhalf.domain.LG0010.dto.LG0021Dto;

public interface OAuthInfoResponse {
    String getEmail();
    String getNickname();
    LG0021Dto getOAuthProvider();
}