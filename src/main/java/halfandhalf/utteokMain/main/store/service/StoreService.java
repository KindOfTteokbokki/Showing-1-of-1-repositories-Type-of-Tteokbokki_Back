package halfandhalf.utteokMain.main.store.service;

import halfandhalf.utteokMain.main.commonDto.QuestionDto;
import halfandhalf.utteokMain.main.store.dto.StoreDto;
import halfandhalf.utteokMain.main.store.entity.StoreEntity;
import halfandhalf.utteokMain.main.store.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public StoreDto findOneTaste(Long id) {
        return storeRepository.findById(id).orElseThrow(NullPointerException::new).changeStoreEntity();
    }

    public StoreDto findTasteInfo(QuestionDto dto, Long id) {
        return null;
    }

    public List<StoreDto> findMyTaste(Long id) {
        return null;
    }

    private StoreDto changeEntity(StoreEntity entity) {
        return changeEntity(entity);
    }
}
