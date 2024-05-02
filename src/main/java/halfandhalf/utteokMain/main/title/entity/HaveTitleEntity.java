package halfandhalf.utteokMain.main.title.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "have_title")
public class HaveTitleEntity {
    @Id @GeneratedValue
    @Column(name = "have_title_seq")
    private Long id;

    private String user_id;
    private Long title_seq;
    private boolean get_title;
}
