package object_oriented.inf.case4;

import object_oriented.oop.abstraction.Image;

public interface ImageStore {
    String upload(Image image, String bucketName);

    Image download(String url);
}





