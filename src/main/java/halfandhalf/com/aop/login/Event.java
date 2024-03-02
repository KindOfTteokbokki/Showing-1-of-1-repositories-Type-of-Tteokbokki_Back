package halfandhalf.com.aop.login;

public interface Event {
    void beforeEvent();
    void middleEvent();
    void afterEvent();
}