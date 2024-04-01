package halfandhalf.utteokMain.loading.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "loading_for_analyze")
@Getter
public class LoadingEntity {
    @Id
    @GeneratedValue
    @Column(name = "loading_seq")
    private Long id;

    private String phrases;

}
