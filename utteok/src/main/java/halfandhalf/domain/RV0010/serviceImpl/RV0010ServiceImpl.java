package halfandhalf.domain.RV0010.serviceImpl;

import halfandhalf.com.exception.FileUploadException;
import halfandhalf.com.util.getDate;
import halfandhalf.domain.RV0010.dao.RV0010Dao;
import halfandhalf.domain.RV0010.dto.RV0010Dto;
import halfandhalf.domain.RV0010.dto.RV0011Dto;
import halfandhalf.domain.RV0010.service.RV0010Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class RV0010ServiceImpl implements RV0010Service {

    @Value("${api.upload.dir.review}")
    private String uploadDir;

    private final RV0010Dao rV0010Dao;
    private static final Logger log = LoggerFactory.getLogger(RV0010ServiceImpl.class);

    @Autowired
    public RV0010ServiceImpl(RV0010Dao rV0010Dao) {
        this.rV0010Dao = rV0010Dao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RV0010Dto> findRecommend() {
        return rV0010Dao.findRecommend();
    }

    @Override
    @Transactional
    public void saveRecommend(RV0010Dto rv0010Dto, MultipartFile file) throws Exception{

        if(StringUtils.isEmpty(rv0010Dto.getContent())) throw new DataIntegrityViolationException("내용을 입력해 주세요");

        try {
            RV0010Dto fileRV = uploadImage(file);
            fileRV.setContent(rv0010Dto.getContent());
            rV0010Dao.saveRecommend(fileRV);
        } catch (NullPointerException e) {
            rV0010Dao.saveRecommend(rv0010Dto);
        } catch (Exception e) {
            log.error("에러 : ", e.getMessage());
            throw new FileUploadException("파일 업로드 오류");
        }
    }

    @Override
    @Transactional
    public List<RV0010Dto> findRecommendByPage(RV0011Dto rv0011Dto) {
        rv0011Dto.setPageNum(rv0011Dto.getSize() * rv0011Dto.getPageNum());
        return rV0010Dao.findRecommendByPage(rv0011Dto);
    }

    public RV0010Dto uploadImage(MultipartFile file) throws Exception {
        RV0010Dto rv0010Dto = null;
        try{
            String nowDate = getDate.getCurrentTime("YYYYMMDD");
            // uploadDir을 사용하면 static처럼 뒤에 계속 날짜가 붙는다.
            String dirPath = uploadDir + nowDate;
            File folder = new File(dirPath);

            if (!folder.exists()) {
                folder.mkdirs();
            }

            String nowTime = getDate.getCurrentTime("HHmmss");
            String fileName = nowTime + file.getOriginalFilename();

            File destination = new File(dirPath + File.separator + fileName);
            file.transferTo(destination);

            rv0010Dto = new RV0010Dto(dirPath, file.getOriginalFilename(), fileName);
        }
        catch (Exception e){
            log.error("에러 : ", e.getMessage());
            throw new FileUploadException("파일 업로드 오류");
        }
        finally {
            return rv0010Dto;
        }
    }

}
