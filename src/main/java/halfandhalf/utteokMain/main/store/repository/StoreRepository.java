package halfandhalf.utteokMain.main.store.repository;

import halfandhalf.utteokMain.main.commonDto.QuestionDto;
import halfandhalf.utteokMain.main.store.entity.StoreEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
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
