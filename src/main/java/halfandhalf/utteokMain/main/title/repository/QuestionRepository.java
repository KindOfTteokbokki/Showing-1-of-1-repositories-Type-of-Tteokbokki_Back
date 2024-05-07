package halfandhalf.utteokMain.main.title.repository;

import halfandhalf.utteokMain.main.commonEntity.QuestionEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    @Query(value = "select * from question_for_select qs" +
            "where 1=1" +
            "and qs.question1 = :question1 " +
            "and qs.question2 = :question2 " +
            "and qs.question3 = :question3 " +
            "and qs.question4 = :question4 " +
            "and qs.question5 = :question5 " +
            "and qs.question6 = :question6 " +
            "and qs.question7 = :question7 " +
            "", nativeQuery = true)
    @Cacheable(value = "questionCache")
    QuestionEntity findByQuestion(
            String question1 ,
            String question2 ,
            String question3 ,
            String question4 ,
            String question5 ,
            String question6 ,
            String question7
    );
}
