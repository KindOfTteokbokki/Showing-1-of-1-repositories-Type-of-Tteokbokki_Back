package halfandhalf.utteokMain.main.title.repository;

import halfandhalf.utteokMain.main.commonDto.QuestionDto;
import halfandhalf.utteokMain.main.store.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    @Query(value = "" +
            "select * from store_info si " +
                "join fetch question_for_select qs on si.question_seq = qs.question_seq " +
                "join fetch menu_img mi on mi.question_seq = qs.question_seq " +
            "where 1=1 " +
                "and qs.question1 = :question1 " +
                "and qs.question2 = :question2 " +
                "and qs.question3 = :question3 " +
                "and qs.question4 = :question4 " +
                "and qs.question5 = :question5 " +
                "and qs.question6 = :question6 " +
                "and qs.question7 = :question7 " +
            "order by rand() " +
            "limit 1", nativeQuery = true)
    StoreEntity findTasteByQuestion(@RequestBody QuestionDto dto);

    @Query(value = "" +
            "select * from store_info si " +
            "join fetch menu_img mi on mi.question_seq = si.question_seq " +
            "join fetch mytaste_count mc on si.store_seq = mc.store_seq " +
            "where 1=1 " +
            "and mc.user_id = :user_id " +
            "order by mc.menu_count desc " +
            "limit 3", nativeQuery = true)
    List<StoreEntity> findTop3MyTasteByUserIdOrderByMenuCountDesc(@Param("user_id") Long id);
}
