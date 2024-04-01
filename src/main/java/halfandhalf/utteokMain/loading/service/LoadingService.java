package halfandhalf.utteokMain.loading.service;

import halfandhalf.utteokMain.loading.dto.LoadingDto;
import halfandhalf.utteokMain.loading.repository.LoadingQueryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LoadingService {

    private final LoadingQueryRepository loadingQueryRepository;

    public LoadingService(LoadingQueryRepository loadingQueryRepository) {
        this.loadingQueryRepository = loadingQueryRepository;
    }

    public LoadingDto findTop1Random() {
        return new LoadingDto(loadingQueryRepository.findTop1Random().getPhrases());
    }
}
