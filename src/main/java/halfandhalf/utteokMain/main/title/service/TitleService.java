package halfandhalf.utteokMain.main.title.service;

import halfandhalf.utteokMain.main.commonDto.QuestionDto;
import halfandhalf.utteokMain.main.title.dto.TitleDto;
import halfandhalf.utteokMain.main.title.entity.HaveTitleEntity;
import halfandhalf.utteokMain.main.title.entity.TitleEntity;
import halfandhalf.utteokMain.main.title.repository.HavaTitleRepository;
import halfandhalf.utteokMain.main.title.repository.QuestionRepository;
import halfandhalf.utteokMain.main.title.repository.TitleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class TitleService {

    private final TitleRepository titleRepository;
    private final QuestionRepository questionRepository;
    private final HavaTitleRepository havaTitleRepository;

    public TitleService(TitleRepository storeRepository, QuestionRepository questionRepository, HavaTitleRepository havaTitleRepository) {
        this.titleRepository = storeRepository;
        this.questionRepository = questionRepository;
        this.havaTitleRepository = havaTitleRepository;
    }

    @Transactional
    public TitleDto findTitleByQuestionAndId(QuestionDto dto, long id) {
        TitleEntity title = Optional.ofNullable(
                        questionRepository.findByQuestion(
                                    dto.getQuestion1() ,
                                    dto.getQuestion2() ,
                                    dto.getQuestion3() ,
                                    dto.getQuestion4() ,
                                    dto.getQuestion5() ,
                                    dto.getQuestion6() ,
                                    dto.getQuestion7()
                        ))
//                titleRepository.findTitle(
//                    dto.getQuestion1() ,
//                    dto.getQuestion2() ,
//                    dto.getQuestion3() ,
//                    dto.getQuestion4() ,
//                    dto.getQuestion5() ,
//                    dto.getQuestion6() ,
//                    dto.getQuestion7()
//                ))
                .orElseGet(() ->
//                        titleRepository.findTitle(
//                        QuestionDto.getInstance().getQuestion1() ,
//                        QuestionDto.getInstance().getQuestion2() ,
//                        QuestionDto.getInstance().getQuestion3() ,
//                        QuestionDto.getInstance().getQuestion4() ,
//                        QuestionDto.getInstance().getQuestion5() ,
//                        QuestionDto.getInstance().getQuestion6() ,
//                        QuestionDto.getInstance().getQuestion7()
                        questionRepository.findByQuestion(
                                QuestionDto.getInstance().getQuestion1() ,
                                QuestionDto.getInstance().getQuestion2() ,
                                QuestionDto.getInstance().getQuestion3() ,
                                QuestionDto.getInstance().getQuestion4() ,
                                QuestionDto.getInstance().getQuestion5() ,
                                QuestionDto.getInstance().getQuestion6() ,
                                QuestionDto.getInstance().getQuestion7()
                )).getTitleEntity();

        if (id != 0L) {
            Optional.ofNullable(
                    havaTitleRepository.findByUserIdAndTitleSeq(id, title.getId())
//                    titleRepository.findTitleByIdAndSeq(id, title.getId())
                    )
                    .orElseGet(() -> {
                        title.getHaveTitleEntity().changeStateHaveTitle();
                        havaTitleRepository.save(title.getHaveTitleEntity());
                        return title.getHaveTitleEntity();
                    });
        }
        return title.changeTitleEntity();
    }

    public List<TitleDto> findHaveTitle(Long userId) {
        return havaTitleRepository.findByUserId(userId)
                .stream()
                .map(HaveTitleEntity::changeHavaTitleEntity)
                .collect(Collectors.toList());
    }

    public List<TitleDto> findAllTitleNotHave(Long userId) {
        return havaTitleRepository.findByIdNotIn(
                    havaTitleRepository.findByUserId(userId)
                            .stream()
                            .filter(e -> Objects.equals(e.getUserId(), userId))
                            .map(HaveTitleEntity::getTitleSeq)
                            .collect(Collectors.toList())
            ).stream().map(HaveTitleEntity::changeHavaTitleEntity).collect(Collectors.toList());
    }
}