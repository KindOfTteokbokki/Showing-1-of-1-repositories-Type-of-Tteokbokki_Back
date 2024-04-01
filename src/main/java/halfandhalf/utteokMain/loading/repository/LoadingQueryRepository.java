package halfandhalf.utteokMain.loading.repository;

import halfandhalf.utteokMain.combination.entity.CombinationEntity;
import halfandhalf.utteokMain.loading.entity.LoadingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoadingQueryRepository extends JpaRepository<LoadingEntity, Long>  {
    @Query(value = "SELECT loading_seq, phrases FROM loading_for_analyze where 1=1 and use_yn  = 'Y' order by RAND() limit 1", nativeQuery = true)
    LoadingEntity findTop1Random();
}
