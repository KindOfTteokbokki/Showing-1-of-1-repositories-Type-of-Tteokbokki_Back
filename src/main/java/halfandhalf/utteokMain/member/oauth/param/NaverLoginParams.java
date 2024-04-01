package halfandhalf.utteokMain.member.oauth.param;

import halfandhalf.domain.LG0010.dto.LG0021Dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@NoArgsConstructor
public class NaverLoginParams implements OAuthLoginParams {
    private String authorizationCode;
    private String state;

    public NaverLoginParams(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    @Override
    public LG0021Dto oAuthProvider() {
        return LG0021Dto.NAVER;
    }

    @Override
    public MultiValueMap<String, String> makeBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", authorizationCode);
        body.add("state", state);
        return body;
    }
}