package design_pattern.struct.adapter.case6;

// 将外部系统A替换成外部系统B
public class BAdaptor implements IA {
    private B b;
    public BAdaptor(B b) {
        this.b= b;
    }
    @Override
    public void fa() {
        //...
        b.fb();
    }
}
