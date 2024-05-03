package halfandhalf.utteokMain.manageEntity;

import javax.persistence.*;

@Entity
@Table(name = "public_code")
public class PublicCode {
    @Id
    @Column(name = "code")
    private String id;

    private String name_ko;
    private String code_category_name_en;
    private char use_yn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code_category_id")
    private PublicCodeCategory publicCodeCategory;
}
