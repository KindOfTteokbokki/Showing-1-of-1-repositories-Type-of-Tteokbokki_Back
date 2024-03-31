package halfandhalf.utteokMain.combination.repository;

import halfandhalf.utteokMain.combination.entity.CombinationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CombinationRepository extends JpaRepository<CombinationEntity, Long>, CombinationQueryRepository {
}
