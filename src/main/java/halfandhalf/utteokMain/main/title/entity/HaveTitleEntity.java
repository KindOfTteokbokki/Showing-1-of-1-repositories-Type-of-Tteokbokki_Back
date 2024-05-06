package halfandhalf.utteokMain.main.title.entity;

import halfandhalf.utteokMain.main.title.dto.TitleDto;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name = "have_title")
public class HaveTitleEntity {
    @Id @GeneratedValue
    @Column(name = "have_title_seq")
    private Long id;

    private Long user_id;
    private Long title_seq;
    private boolean get_title;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "title_seq")
    TitleEntity titleEntity;

    public void haveTitle(){
        this.get_title = true;
    }

    public TitleDto changeHavaTitleEntity() {
        return TitleDto.builder()
                .user_id(user_id)
                .title_seq(title_seq)
                .get_title(get_title)
                .build();
    }
}
