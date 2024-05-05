package halfandhalf.utteokMain.main.title.service;

import halfandhalf.utteokMain.main.commonDto.QuestionDto;
import halfandhalf.utteokMain.main.store.entity.StoreEntity;
import halfandhalf.utteokMain.main.title.dto.StoreDto;
import halfandhalf.utteokMain.main.title.dto.TitleDto;
import halfandhalf.utteokMain.main.title.repository.StoreCountRepository;
import halfandhalf.utteokMain.main.title.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TitleService {

    private final StoreRepository storeRepository;
    private final StoreCountRepository storeCountRepository;

    public TitleService(StoreRepository storeRepository, StoreCountRepository storeCountRepository) {
        this.storeRepository = storeRepository;
        this.storeCountRepository = storeCountRepository;
    }

    public TitleDto findOneTaste(Long id) {
        return storeRepository.findById(id).orElseThrow(NullPointerException::new).changeStoreEntity();
    }

    public TitleDto findStoreInfo(QuestionDto dto, Long id) {
        StoreEntity storeInfo = Optional.ofNullable(storeRepository.findTasteByQuestion(dto))
                .orElseGet(() -> storeRepository.findTasteByQuestion(QuestionDto.getInstance()));

        if(!id.equals(0L)) {
            storeInfo.getStoreCountEntity().incrementCount();
            Optional.ofNullable(storeCountRepository.findByUserIdAndSeq(id, storeInfo.getId()))
                    .orElseGet(()->storeCountRepository.save(storeInfo.getStoreCountEntity()));
        }

        return storeInfo.changeStoreEntity();
    }

    public List<TitleDto> findMyTaste(Long userId) {
        return storeRepository.findTop3MyTasteByUserIdOrderByMenuCountDesc(userId)
                .stream()
                .map(StoreEntity::changeStoreEntity)
                .collect(Collectors.toList());
    }
}
