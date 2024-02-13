package halfandhalf.domain.LG0010.oauth.param;

import halfandhalf.domain.LG0010.dto.LG0021Dto;
import org.springframework.util.MultiValueMap;

public interface OAuthLoginParams {
    LG0021Dto oAuthProvider();
    MultiValueMap<String, String> makeBody();
}
