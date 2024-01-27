package domain.BA0010.serviceImpl;

import domain.BA0010.dao.BA0010Dao;
import domain.BA0010.dto.BA0010Dto;
import domain.BA0010.service.BA0010Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BA0010ServiceImpl implements BA0010Service {

    @Autowired
    private BA0010Dao BA0010Dao;

    public List<BA0010Dto> getApi() throws Exception {
        return BA0010Dao.getApi();
    }

}
