package comp.case5;

public class CustomizedFeignClient extends FeignClient {
    public static void main(String[] args) {
        // 调用
        FeignClient client = new CustomizedFeignClient();
        demofunction(client);
    }

    public static void demofunction(FeignClient feignClient) {
        //...
        String url = null;
        feignClient.encode(url);
        //...
    }

    @Override
    public void encode(String url) { //...重写encode的实现...
    }
}


