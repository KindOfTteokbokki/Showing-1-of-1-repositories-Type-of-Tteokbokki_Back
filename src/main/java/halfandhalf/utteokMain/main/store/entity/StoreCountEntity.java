package halfandhalf.utteokMain.main.store.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "mytaste_count")
public class StoreCountEntity {
    @Id
    @GeneratedValue
    @Column(name = "mytaste_count_seq")
    private Long id;

    private Long user_id;
    private Long store_seq;
    private int menu_count;
    private boolean get_title;

    public void incrementCount() {
        this.menu_count++;
    }
}
