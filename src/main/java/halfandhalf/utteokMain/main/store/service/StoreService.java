package halfandhalf.utteokMain.main.store.service;

import halfandhalf.utteokMain.main.commonDto.QuestionDto;
import halfandhalf.utteokMain.main.store.dto.StoreDto;
import halfandhalf.utteokMain.main.store.entity.StoreEntity;
import halfandhalf.utteokMain.main.store.repository.StoreCountRepository;
import halfandhalf.utteokMain.main.store.repository.StoreRepository;
import halfandhalf.utteokMain.main.title.repository.QuestionRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;
    private final QuestionRepository questionRepository;
    private final StoreCountRepository storeCountRepository;

    public StoreService(StoreRepository storeRepository, QuestionRepository questionRepository, StoreCountRepository storeCountRepository) {
        this.storeRepository = storeRepository;
        this.questionRepository = questionRepository;
        this.storeCountRepository = storeCountRepository;
    }

    @Cacheable(value = "findTaste")
    public StoreDto findOneTaste(Long seq) {
        return storeRepository.findById(seq).orElseThrow(NullPointerException::new).changeStoreEntity();
    }

    public StoreDto findStoreInfo(QuestionDto dto, Long userId) {
        StoreEntity storeInfo = Optional.ofNullable(questionRepository.findByQuestion(
                        dto.getQuestion1(),
                        dto.getQuestion2(),
                        dto.getQuestion3(),
                        dto.getQuestion4(),
                        dto.getQuestion5(),
                        dto.getQuestion6(),
                        dto.getQuestion7()
                ))
                .orElseGet(() -> questionRepository.findByQuestion(
                        QuestionDto.getInstance().getQuestion1() ,
                        QuestionDto.getInstance().getQuestion2() ,
                        QuestionDto.getInstance().getQuestion3() ,
                        QuestionDto.getInstance().getQuestion4() ,
                        QuestionDto.getInstance().getQuestion5() ,
                        QuestionDto.getInstance().getQuestion6() ,
                        QuestionDto.getInstance().getQuestion7()
                ))
                .getStoreEntity();
//
//        StoreEntity storeInfo = Optional.ofNullable(storeRepository.findTasteByQuestion(
//                        dto.getQuestion1(),
//                        dto.getQuestion2(),
//                        dto.getQuestion3(),
//                        dto.getQuestion4(),
//                        dto.getQuestion5(),
//                        dto.getQuestion6(),
//                        dto.getQuestion7()
//                ))
//                .orElseGet(() -> storeRepository.findTasteByQuestion(
//                        QuestionDto.getInstance().getQuestion1() ,
//                        QuestionDto.getInstance().getQuestion2() ,
//                        QuestionDto.getInstance().getQuestion3() ,
//                        QuestionDto.getInstance().getQuestion4() ,
//                        QuestionDto.getInstance().getQuestion5() ,
//                        QuestionDto.getInstance().getQuestion6() ,
//                        QuestionDto.getInstance().getQuestion7()
//                ));

        if(!userId.equals(0L)) {
            storeInfo.getStoreCountEntity().incrementCount();
            Optional.ofNullable(storeCountRepository.findByUserIdAndId(userId, storeInfo.getId()))
                    .orElseGet(()->storeCountRepository.save(storeInfo.getStoreCountEntity()));
        }

        return storeInfo.changeStoreEntity();
    }

    public List<StoreDto> findMyTaste(Long userId) {
        return storeRepository.findTop3MyTasteByUserIdOrderByMenuCountDesc(userId)
                .stream()
                .map(StoreEntity::changeStoreEntity)
                .collect(Collectors.toList());
    }
}
