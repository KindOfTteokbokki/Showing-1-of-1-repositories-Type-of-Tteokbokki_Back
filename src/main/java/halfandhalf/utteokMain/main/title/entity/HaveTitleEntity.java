package halfandhalf.utteokMain.main.title.entity;

import halfandhalf.utteokMain.main.title.dto.TitleDto;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "have_title")
public class HaveTitleEntity {
    @Id @GeneratedValue
    @Column(name = "have_title_seq")
    private Long id;

    private Long userId;
    private Long titleSeq;
    private boolean get_title;

    @OneToOne(fetch = FetchType.LAZY)
    TitleEntity titleEntity;

    public void changeStateHaveTitle(){
        this.get_title = true;
    }

    public TitleDto changeHavaTitleEntity() {
        return TitleDto.builder()
                .user_id(userId)
                .title_seq(titleSeq)
                .get_title(get_title)
                .build();
    }
}
