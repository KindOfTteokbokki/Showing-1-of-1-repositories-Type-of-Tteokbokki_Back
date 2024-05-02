package halfandhalf.utteokMain.main.store.entity;

import halfandhalf.utteokMain.main.commonEntity.QuestionEntity;
import halfandhalf.utteokMain.manageEntity.FileManageEntity;
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

    @OneToOne(mappedBy = "question_seq", fetch = FetchType.LAZY)
    MenuImgEntity menuImgEntity;

    @OneToOne(mappedBy = "question_seq", fetch = FetchType.LAZY)
    QuestionEntity questionEntity;

    @OneToOne(mappedBy = "mytaste_count_seq", fetch = FetchType.LAZY)
    StoreCountEntity storeCountEntity;
}
