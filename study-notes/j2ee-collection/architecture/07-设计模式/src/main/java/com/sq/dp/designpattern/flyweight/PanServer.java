package com.sq.dp.designpattern.flyweight;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 享元工厂
 */
public class PanServer {
    private AtomicInteger linkId = new AtomicInteger();

    private Map<String, Resource> resources = new HashMap<>();
    private Map<String, ResourceLink> userLinks = new HashMap<>();

    public static String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法! ");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    public void connect(PanClient client) {
        System.out.println("客户端: " + client.getId() + "连接上来了...");
    }

    public void uploadFile(User user, LocalFile localFile) {
        System.out.println("用户: " + user.getUsername() + " 上传了文件: " + localFile.getName());
        String hash = hash(localFile);

        Resource resource = resources.get(hash);
        if (resource == null) {
            resource = new Resource(hash, localFile, user);
            resources.put(hash, resource);
        } else {
            System.out.println("文件: " + localFile.getName() + ", hash=" + hash + " 已经存在......");
        }
        ResourceLink link = new ResourceLink(linkId.getAndIncrement() + "", user.getUid(), hash);
        userLinks.put(user.getUid() + "_" + hash, link);
    }

    public String hash(LocalFile localFile) {
        return stringToMD5(localFile.getName() + localFile.getSize());
    }
}
