package halfandhalf.utteokMain.loading.entity;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "loading_for_analyze")
@Getter
public class LoadingEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "loading_seq")
    private Long id;

    private String phrases;

    public LoadingEntity() {
    }
}
