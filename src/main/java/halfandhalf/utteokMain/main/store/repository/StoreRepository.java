package halfandhalf.utteokMain.main.store.repository;

import halfandhalf.utteokMain.main.store.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
//    @Query(value = "", nativeQuery = true)
//    public StoreEntity
}
