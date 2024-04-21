package halfandhalf.com.aop;


import halfandhalf.com.exception.FileUploadException;
import halfandhalf.com.exception.ForbiddenException;
import halfandhalf.com.exception.UnauthorizedException;
import halfandhalf.com.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.UnavailableException;

@Component
@Aspect
@Slf4j
public class ResponseMessage {

    @Around("execution(public * halfandhalf.utteokMain.*.*.*Controller.*(..))")
    public ResponseEntity<?> allController(ProceedingJoinPoint jp) throws Throwable {
        try {
            Object proceed = jp.proceed();

            System.out.println("test aop : " + jp.getArgs().getClass().getSimpleName());

            return ResponseEntity.ok(proceed);
        }
        catch(FileUploadException | NullPointerException | IllegalArgumentException e){
            // 그 외 에러의 경우 400 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(halfandhalf.com.config.ResponseMessage.valueOfCode("InvalidParams").getMessage());
        }
        catch(UnauthorizedException e){
            // 그 외 에러의 경우 401 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(halfandhalf.com.config.ResponseMessage.valueOfCode("Unauthorized").getMessage());
        }
        catch(UnavailableException e){
            // 그 외 에러의 경우 401 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(halfandhalf.com.config.ResponseMessage.valueOfCode("Unavailable").getMessage());
        }
        catch(ForbiddenException e){
            // 그 외 에러의 경우 403 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(halfandhalf.com.config.ResponseMessage.valueOfCode("Forbidden").getMessage());
        }
        catch(ValidationException e){
            // 그 외 에러의 경우 403 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(halfandhalf.com.config.ResponseMessage.valueOfCode("Validation").getMessage());
        }
        catch(NotFoundException e){
            // 그 외 에러의 경우 404 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(halfandhalf.com.config.ResponseMessage.valueOfCode("NotFound").getMessage());
        }
        catch(HttpClientErrorException.Conflict e){
            // 그 외 에러의 경우 409 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(halfandhalf.com.config.ResponseMessage.valueOfCode("Conflict").getMessage());
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(halfandhalf.com.config.ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

}
