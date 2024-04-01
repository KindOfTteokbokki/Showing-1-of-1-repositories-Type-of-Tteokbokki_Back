package halfandhalf.utteokMain.member.oauth.client;

import halfandhalf.domain.LG0010.dto.LG0021Dto;
import halfandhalf.utteokMain.member.oauth.param.OAuthLoginParams;
import halfandhalf.utteokMain.member.oauth.response.OAuthInfoResponse;

public interface OAuthApiClient {
    LG0021Dto oAuthProvider();
    String requestAccessToken(OAuthLoginParams params);
    OAuthInfoResponse requestOauthInfo(String accessToken);
}