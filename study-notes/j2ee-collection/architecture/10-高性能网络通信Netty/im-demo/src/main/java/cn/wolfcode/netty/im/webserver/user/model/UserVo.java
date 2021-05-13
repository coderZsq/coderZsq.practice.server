package cn.wolfcode.netty.im.webserver.user.model;

/**
 * @author Leon
 */
public class UserVo {

    private String id;
    private String name;
    private String avatar;
    private String signature;
    private Integer sex;

    public UserVo() {
    }

    public UserVo(String id, String name, String avatar, String signature, Integer sex) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.signature = signature;
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
