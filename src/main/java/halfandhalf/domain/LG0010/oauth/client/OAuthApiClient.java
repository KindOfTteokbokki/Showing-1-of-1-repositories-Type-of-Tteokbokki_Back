package halfandhalf.domain.LG0010.oauth.client;

import halfandhalf.domain.LG0010.dto.LG0021Dto;
import halfandhalf.domain.LG0010.oauth.param.OAuthLoginParams;
import halfandhalf.domain.LG0010.oauth.response.OAuthInfoResponse;

public interface OAuthApiClient {
    LG0021Dto oAuthProvider();
    String requestAccessToken(OAuthLoginParams params);
    OAuthInfoResponse requestOauthInfo(String accessToken);
}