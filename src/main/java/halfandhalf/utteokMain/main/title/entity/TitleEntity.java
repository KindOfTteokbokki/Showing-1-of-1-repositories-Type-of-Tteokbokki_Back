package halfandhalf.utteokMain.main.title.entity;

import halfandhalf.utteokMain.main.commonEntity.QuestionEntity;
import halfandhalf.utteokMain.main.title.dto.TitleDto;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "title_code")
    TitleImgEntity titleImgEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "question_seq")
    QuestionEntity questionEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "titleSeq")
    HaveTitleEntity haveTitleEntity;

    public TitleDto changeTitleEntity() {
        return TitleDto.builder()
                .title_seq(id)
                .title_name(title_code)
                .icorn_file_path(titleImgEntity.getIcornFileManageEntity().getFile_path())
                .icorn_file_original_name(titleImgEntity.getIcornFileManageEntity().getFile_original_name())
                .icorn_file_masking_name(titleImgEntity.getIcornFileManageEntity().getFile_masking_name())
                .img_file_path(titleImgEntity.getImgFfileManageEntity().getFile_path())
                .img_file_original_name(titleImgEntity.getImgFfileManageEntity().getFile_original_name())
                .img_file_masking_name(titleImgEntity.getImgFfileManageEntity().getFile_masking_name())
                .question1(questionEntity.getQuestion1())
                .question2(questionEntity.getQuestion2())
                .question3(questionEntity.getQuestion3())
                .question4(questionEntity.getQuestion4())
                .question5(questionEntity.getQuestion5())
                .question6(questionEntity.getQuestion6())
                .question7(questionEntity.getQuestion7())
//                .title_count()
                .user_id(haveTitleEntity.getUserId())
                .get_title(haveTitleEntity.isGet_title())
                .build();
    }
}
