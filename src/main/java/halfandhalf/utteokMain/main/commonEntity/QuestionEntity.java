package halfandhalf.utteokMain.main.commonEntity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "question_for_select")
public class QuestionEntity {
    @Id @GeneratedValue
    @Column(name = "question_seq")
    private Long id;

    private String category;
    private String code;
    private String question1;
    private String question2;
    private String question3;
    private String question4;
    private String question5;
    private String question6;
    private String question7;
}
