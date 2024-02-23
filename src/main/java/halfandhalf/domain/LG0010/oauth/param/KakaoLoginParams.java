package halfandhalf.domain.LG0010.oauth.param;

import halfandhalf.domain.LG0010.dto.LG0021Dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@NoArgsConstructor
public class KakaoLoginParams implements OAuthLoginParams {
    private String authorizationCode;

    public KakaoLoginParams(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    @Override
    public LG0021Dto oAuthProvider() {
        return LG0021Dto.KAKAO;
    }

    @Override
    public MultiValueMap<String, String> makeBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", authorizationCode);
        return body;
    }
}