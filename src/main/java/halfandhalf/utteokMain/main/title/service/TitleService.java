package halfandhalf.utteokMain.main.title.service;

import halfandhalf.utteokMain.main.commonDto.QuestionDto;
import halfandhalf.utteokMain.main.title.dto.TitleDto;
import halfandhalf.utteokMain.main.title.entity.HaveTitleEntity;
import halfandhalf.utteokMain.main.title.entity.TitleEntity;
import halfandhalf.utteokMain.main.title.repository.HavaTitleRepository;
import halfandhalf.utteokMain.main.title.repository.TitleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class TitleService {

    private final TitleRepository titleRepository;
    private final HavaTitleRepository havaTitleRepository;

    public TitleService(TitleRepository storeRepository, HavaTitleRepository havaTitleRepository) {
        this.titleRepository = storeRepository;
        this.havaTitleRepository = havaTitleRepository;
    }

    @Transactional
    public TitleDto findTitleByQuestionAndId(QuestionDto dto, long id) {
        TitleEntity title = Optional.ofNullable(titleRepository.findTitle(dto))
                .orElseGet(() -> titleRepository.findTitle(QuestionDto.getInstance()));

        if (id != 0L) {
            Optional.ofNullable(titleRepository.findTitleByIdAndSeq(id, title.getId()))
                    .orElseGet(() -> {
                        title.getHaveTitleEntity().haveTitle();
                        return titleRepository.save(title);
                    });
        }
        return title.changeTitleEntity();
    }

    public List<TitleDto> findHaveTitle(Long userId) {
        return havaTitleRepository.findByUseId(userId)
                .stream()
                .map(HaveTitleEntity::changeHavaTitleEntity)
                .collect(Collectors.toList());
    }

    public List<TitleDto> findAllTitleNotHave(Long userId) {
        return havaTitleRepository.findByIdNotIn(
                    havaTitleRepository.findByUseId(userId)
                            .stream()
                            .filter(e -> Objects.equals(e.getUser_id(), userId))
                            .map(HaveTitleEntity::getTitle_seq)
                            .collect(Collectors.toList())
            ).stream().map(HaveTitleEntity::changeHavaTitleEntity).collect(Collectors.toList());
    }
}