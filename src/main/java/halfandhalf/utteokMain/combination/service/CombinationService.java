package halfandhalf.utteokMain.combination.service;

import halfandhalf.utteokMain.combination.dto.CombinationDto;
import halfandhalf.utteokMain.combination.entity.CombinationEntity;
import halfandhalf.utteokMain.combination.repository.CombinationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CombinationService {
    private final CombinationRepository combinationRepository;

    public CombinationService(CombinationRepository combinationRepository) {
        this.combinationRepository = combinationRepository;
    }

    public CombinationDto findOneCombination(Long id) {
        return new CombinationDto(combinationRepository.findById(id));
    }

    public List<CombinationEntity> findCombination() {
        return combinationRepository.findTop4Random();
    }
}
