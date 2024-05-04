package halfandhalf.utteokMain.main.store.repository;

import halfandhalf.utteokMain.main.store.entity.StoreCountEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StoreCountRepository extends JpaRepository<StoreCountEntity, Long> {
    StoreCountEntity findByUserIdAndSeq(@Param("user_id") Long userId, @Param("store_id") Long seq);
}
