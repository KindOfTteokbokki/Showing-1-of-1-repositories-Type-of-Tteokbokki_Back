package halfandhalf;

import halfandhalf.utteokMain.questionAndAnswer.entity.QaEntity;
import halfandhalf.utteokMain.questionAndAnswer.repository.QaRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class utteok {
    public static void main(String[] args) {
        SpringApplication.run(utteok.class, args);
    }
}