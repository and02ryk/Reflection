import interfaces.SomeInterface;
import interfaces.SomeOtherInterface;

public class SomeBean {
    @AutoInjectable
    private SomeInterface _field1;

    @AutoInjectable
    private SomeOtherInterface _field2;

    public void foo() {
        _field1.doSomething();
        _field2.doSomething();
    }
}