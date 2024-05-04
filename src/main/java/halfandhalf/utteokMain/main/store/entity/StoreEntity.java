package halfandhalf.utteokMain.main.store.entity;

import halfandhalf.utteokMain.main.commonEntity.QuestionEntity;
import halfandhalf.utteokMain.main.store.dto.StoreDto;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "store_info")
public class StoreEntity {
    @Id @GeneratedValue
    @Column(name = "store_seq")
    private Long id;

    private String store_code;
    private String store_name;
    private String store_address;
    private String menu_name;
    private String review;
    private char franchise_yn;
    private Long question_seq;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "question_seq")
    MenuImgEntity menuImgEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "question_seq")
    QuestionEntity questionEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "mytaste_count_seq")
    StoreCountEntity storeCountEntity;

    public StoreDto changeStoreEntity() {
        return StoreDto.builder()
                .store_seq(id)
                .store_name(store_name)
                .store_address(store_address)
                .menu_name(menu_name)
                .review(review)
                .file_path(menuImgEntity.getFileManageEntity().getFile_path())
                .file_original_name(menuImgEntity.getFileManageEntity().getFile_original_name())
                .file_masking_name(menuImgEntity.getFileManageEntity().getFile_masking_name())
                .question1(questionEntity.getQuestion1())
                .question2(questionEntity.getQuestion2())
                .question3(questionEntity.getQuestion3())
                .question4(questionEntity.getQuestion4())
                .question5(questionEntity.getQuestion5())
                .question6(questionEntity.getQuestion6())
                .question7(questionEntity.getQuestion7())
                .menu_count(storeCountEntity.getMenu_count())
                .user_id(storeCountEntity.getUser_id())
                .build();
    }
}
