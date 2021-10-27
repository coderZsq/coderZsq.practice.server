package object_oriented.inf.case3;

import object_oriented.oop.abstraction.Image;

// AliyunImageStore类的使用举例
public class ImageProcessingJob {
    private static final String BUCKET_NAME = "ai_images_bucket";
    //...省略其他无关代码...

    public void process() {
        Image image = new Image(); //处理图片，并封装为Image对象
        AliyunImageStore imageStore = new AliyunImageStore(/*省略参数*/);
        imageStore.createBucketIfNotExisting(BUCKET_NAME);
        String accessToken = imageStore.generateAccessToken();
        imageStore.uploadToAliyun(image, BUCKET_NAME, accessToken);
    }

}