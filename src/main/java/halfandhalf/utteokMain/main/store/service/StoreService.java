package halfandhalf.utteokMain.main.store.service;

import halfandhalf.utteokMain.main.commonDto.QuestionDto;
import halfandhalf.utteokMain.main.store.dto.StoreDto;
import halfandhalf.utteokMain.main.store.entity.StoreEntity;
import halfandhalf.utteokMain.main.store.repository.StoreCountRepository;
import halfandhalf.utteokMain.main.store.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreCountRepository storeCountRepository;

    public StoreService(StoreRepository storeRepository, StoreCountRepository storeCountRepository) {
        this.storeRepository = storeRepository;
        this.storeCountRepository = storeCountRepository;
    }

    public StoreDto findOneTaste(Long id) {
        return storeRepository.findById(id).orElseThrow(NullPointerException::new).changeStoreEntity();
    }

    public StoreDto findStoreInfo(QuestionDto dto, Long id) {
        StoreEntity myTaste = Optional.ofNullable(storeRepository.findTasteByQuestion(dto))
                .orElseGet(() -> storeRepository.findTasteByQuestion(QuestionDto.getInstance()));

        if(!id.equals(0L)) {
            myTaste.getStoreCountEntity().incrementCount();
            Optional.ofNullable(storeCountRepository.findByUserIdAndSeq(id, myTaste.getId()))
                    .orElseGet(()->storeCountRepository.save(myTaste.getStoreCountEntity()));
        }

        return myTaste.changeStoreEntity();
    }

    public List<StoreDto> findMyTaste(Long userId) {
        return storeRepository.findTop3MyTasteByUserIdOrderByMenuCountDesc(userId)
                .stream()
                .map(StoreEntity::changeStoreEntity)
                .collect(Collectors.toList());
    }
}
