package inf.case2;

public class MockInteface {
    protected MockInteface() {}
    public void funcA() throws MethodUnSupportedException {
        throw new MethodUnSupportedException();
    }
}
