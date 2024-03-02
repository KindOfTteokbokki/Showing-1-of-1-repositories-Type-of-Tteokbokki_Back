package halfandhalf.com.aop.login;

import halfandhalf.com.annotation.LoginCheck;
import org.springframework.stereotype.Service;

@Service
public class EventImpl implements Event{

    @Override
    @LoginCheck
    public void beforeEvent() {
        System.out.println("Before Event");
    }

    @Override
    public void middleEvent() {
        System.out.println("Middle Event");
    }

    @Override
    public void afterEvent() {
        System.out.println("After Event");
    }
}