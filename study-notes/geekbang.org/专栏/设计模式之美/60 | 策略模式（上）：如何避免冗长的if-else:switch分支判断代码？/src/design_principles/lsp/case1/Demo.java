package design_principles.lsp.case1;

public class Demo {
    public void demoFunction(Transporter transporter) {
        Request request = new Request();
        //...省略设置request中数据值的代码...
        Response response = transporter.sendRequest(request);
        //...省略其他逻辑...
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.demoFunction(new SecurityTransporter(null, null, null));
    }
}
