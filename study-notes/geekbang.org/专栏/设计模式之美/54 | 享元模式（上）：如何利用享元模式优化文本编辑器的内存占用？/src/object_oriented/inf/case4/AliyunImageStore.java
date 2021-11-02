package object_oriented.inf.case4;

import object_oriented.oop.abstraction.Image;

public class AliyunImageStore implements ImageStore {
    //...省略属性、构造函数等...

    @Override
    public String upload(Image image, String bucketName) {
        createBucketIfNotExisting(bucketName);
        String accessToken = generateAccessToken();
        //...上传图片到阿里云...
        //...返回图片在阿里云上的地址(url)...
        return null;
    }

    @Override
    public Image download(String url) {
        String accessToken = generateAccessToken();
        //...从阿里云下载图片...
        return null;
    }

    private void createBucketIfNotExisting(String bucketName) {
        // ...创建bucket...
        // ...失败会抛出异常..
    }

    private String generateAccessToken() {
        // ...根据accesskey/secrectkey等生成access token
        return null;
    }
}