package cn.wolfcode.netty.im.webserver.user.model;

import cn.wolfcode.netty.im.webserver.base.model.BaseModel;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;


/**
 * @author Leon
 */
public class UserMessageEntity extends BaseModel {
    private static final long serialVersionUID = 1L;

    public static final int READ_YES = 1;
    public static final int READ_NO = 0;


    /**
     * 发送人
     */
    private String sendUser;
    /**
     * 发送人昵称或姓名
     */
    private String sendUsername;
    /**
     * 发送人头像
     */
    private String avatar;
    /**
     * 接收人
     */
    private String receiveUser;
    /**
     * 群ID
     */
    private String groupId;
    /**
     * 是否已读 0 未读  1 已读
     */
    private Integer isRead;
    /**
     * 类型 0 单聊消息  1 群消息
     */
    private Integer type = 0;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 唯一标识
     */
    @JsonAlias("deviceId")
    @JSONField(name = "deviceId")
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSendUsername() {
        return sendUsername;
    }

    public void setSendUsername(String sendUsername) {
        this.sendUsername = sendUsername;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = StringUtils.isNotEmpty(avatar) ? avatar : "/static/images/0.jpg";
    }

    /**
     * 设置：发送人
     */
    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    /**
     * 获取：发送人
     */
    public String getSendUser() {
        return sendUser;
    }

    /**
     * 设置：接收人
     */
    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    /**
     * 获取：接收人
     */
    public String getReceiveUser() {
        return receiveUser;
    }

    /**
     * 设置：群ID
     */
    public void setGroupId(String groupId) {
        if (StringUtils.isNotEmpty(groupId)) {
            setType(1);
        }
        this.groupId = groupId;
    }

    /**
     * 获取：群ID
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置：是否已读 0 未读  1 已读
     */
    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    /**
     * 获取：是否已读 0 未读  1 已读
     */
    public Integer getIsRead() {
        return isRead;
    }

    /**
     * 设置：类型 0 单聊消息  1 群消息
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：类型 0 单聊消息  1 群消息
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置：消息内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：消息内容
     */
    public String getContent() {
        return content;
    }


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
