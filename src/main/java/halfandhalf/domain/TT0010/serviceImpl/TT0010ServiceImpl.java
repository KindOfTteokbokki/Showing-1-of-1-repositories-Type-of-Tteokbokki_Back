package halfandhalf.domain.TT0010.serviceImpl;

import halfandhalf.domain.TT0010.dao.TT0010Dao;
import halfandhalf.domain.TT0010.dto.TT0010Dto;
import halfandhalf.domain.TT0010.dto.TT0011Dto;
import halfandhalf.domain.TT0010.dto.TT0012Dto;
import halfandhalf.domain.TT0010.service.TT0010Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class TT0010ServiceImpl implements TT0010Service {

    private final TT0010Dao tT0010Dao;

    @Autowired
    public TT0010ServiceImpl(TT0010Dao tT0010Dao) {
        this.tT0010Dao = tT0010Dao;
    }

    @Override
    @Transactional
    public TT0010Dto findTitle(TT0010Dto tT0010Dto, Long user_id) throws Exception {
        TT0010Dto title = tT0010Dao.findTitle(tT0010Dto);

        if(ObjectUtils.isEmpty(title)) title = tT0010Dao.findTitle(new TT0010Dto("ch100", "ch200", "ch300", "ch400", "ch500", "ch600", "ch700"));

        if(0 != user_id.intValue()) {
            TT0011Dto tT0011Dto = new TT0011Dto(user_id, title.getTitle_seq());
            Optional<Boolean> getTitle = Optional.ofNullable(tT0010Dao.findTitleByIdSeq(tT0011Dto))
                    .map(TT0011Dto::isGet_title);
            if(getTitle.isEmpty()) {
                tT0011Dto.setGet_title(true);
                tT0010Dao.insertHaveTitle(tT0011Dto);
            }
        }
        return title;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TT0010Dto> findHaveTitle(Long userId) {
        return tT0010Dao.findHaveTitle(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TT0012Dto> findAllTitleFromUser(Long userId) {
        LinkedList<TT0012Dto> allTitle = tT0010Dao.findAllTitleFromUser(userId);
        for(int i=0; i<allTitle.size(); i++) {
            if(userId.equals(allTitle.get(i).getUser_id())) allTitle.remove(i);
        }
        return allTitle;
    }

    @Override
    public TT0012Dto findCountTitle(Long userId) {
        return tT0010Dao.findCountTitle(userId);
    }
}
