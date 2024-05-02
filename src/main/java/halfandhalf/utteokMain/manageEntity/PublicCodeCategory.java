package halfandhalf.utteokMain.manageEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "public_code_category")
public class PublicCodeCategory {
    @Id @GeneratedValue
    @Column(name = "code_category_id")
    private Long id;

    @NotNull
    private String name_ko;
    @NotNull
    private String name_en;
}
