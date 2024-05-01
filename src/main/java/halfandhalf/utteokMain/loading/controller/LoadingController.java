package halfandhalf.utteokMain.loading.controller;


import halfandhalf.com.config.ResponseMessage;
import halfandhalf.domain.LD0010.service.LD0010Service;
import halfandhalf.utteokMain.loading.service.LoadingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class LoadingController {

    private final LoadingService loadingService;

    public LoadingController(LoadingService loadingService) {
        this.loadingService = loadingService;
    }

    /*
     *  로딩 페이지
     */
    @GetMapping(value="/loadingPage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loading() {
        return ResponseEntity.ok(loadingService.findTop1Random());
    }

}
