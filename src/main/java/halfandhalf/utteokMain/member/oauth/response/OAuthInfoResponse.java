package halfandhalf.utteokMain.member.oauth.response;

import halfandhalf.utteokMain.member.dto.MemberRoute;

public interface OAuthInfoResponse {
    String getEmail();
    String getNickname();
    MemberRoute getOAuthProvider();
}