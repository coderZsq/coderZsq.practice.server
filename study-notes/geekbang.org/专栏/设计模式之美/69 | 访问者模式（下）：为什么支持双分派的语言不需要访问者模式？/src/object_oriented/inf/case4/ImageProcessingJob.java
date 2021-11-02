package object_oriented.inf.case4;

import object_oriented.oop.abstraction.Image;

// ImageStore的使用举例
public class ImageProcessingJob {
    private static final String BUCKET_NAME = "ai_images_bucket";
    //...省略其他无关代码...

    public void process() {
        Image image = new Image();//处理图片，并封装为Image对象
        ImageStore imageStore = new PrivateImageStore();
        imageStore.upload(image, BUCKET_NAME);
    }
}
