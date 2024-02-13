package halfandhalf.domain.LG0010.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LG0020Dto {

    private Long id;

    private String email;

    private String nickname;

    private LG0021Dto oAuthProvider;

    @Builder
    public LG0020Dto(String email, String nickname, LG0021Dto oAuthProvider) {
        this.email = email;
        this.nickname = nickname;
        this.oAuthProvider = oAuthProvider;
    }
}