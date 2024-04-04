package halfandhalf.utteokMain.member.entity;

import halfandhalf.utteokMain.member.dto.MemberRoute;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "oauth_login")
@Getter
public class MemberEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String nickname;

    @Column(name = "utteok_nickname")
    private String utteokNickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "oauthprovider")
    private MemberRoute oauthProvider;

    private LocalDateTime create_date;

    private LocalDateTime recent_date;

    public void changeUtteok_nickname(String utteok_nickname) {
        this.utteokNickname = utteok_nickname;
    }

    public MemberEntity() {
    }

    @Builder
    public MemberEntity(String email, String nickname, MemberRoute oAuthProvider) {
        this.email = email;
        this.nickname = nickname;
        this.oauthProvider = oAuthProvider;
    }
}
