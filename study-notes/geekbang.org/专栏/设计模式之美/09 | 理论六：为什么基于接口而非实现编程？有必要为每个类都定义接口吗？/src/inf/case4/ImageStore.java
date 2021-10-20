package inf.case4;

import oop.abstraction.Image;

public interface ImageStore {
    String upload(Image image, String bucketName);

    Image download(String url);
}





