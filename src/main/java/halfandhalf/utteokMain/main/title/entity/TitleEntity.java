package halfandhalf.utteokMain.main.title.entity;

import halfandhalf.utteokMain.main.commonEntity.QuestionEntity;
import halfandhalf.utteokMain.main.store.entity.MenuImgEntity;
import halfandhalf.utteokMain.main.store.entity.StoreEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "title_info")
public class TitleEntity {
    @Id @GeneratedValue
    @Column(name = "title_seq")
    private Long id;

    private Long question_seq;
    private String title_code;

    @OneToOne(mappedBy = "title_code", fetch = FetchType.LAZY)
    TitleImgEntity menuImgEntity;

    @OneToOne(mappedBy = "question_seq", fetch = FetchType.LAZY)
    QuestionEntity questionEntity;

    @OneToOne(mappedBy = "title_seq", fetch = FetchType.LAZY)
    HaveTitleEntity haveTitleEntity;

}
