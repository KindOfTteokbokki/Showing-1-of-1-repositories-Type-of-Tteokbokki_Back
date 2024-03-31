package halfandhalf.utteokMain.combination.repository;

import halfandhalf.utteokMain.combination.entity.CombinationEntity;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CombinationQueryRepository {
    @Query(value = "SELECT * FROM Utteok_combination order by RAND() limit 4", nativeQuery = true)
    List<CombinationEntity> findTop4Random();
}
