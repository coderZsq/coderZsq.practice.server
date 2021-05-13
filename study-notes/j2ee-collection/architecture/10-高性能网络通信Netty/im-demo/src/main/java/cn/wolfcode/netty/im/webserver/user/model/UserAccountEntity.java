package cn.wolfcode.netty.im.webserver.user.model;

import cn.wolfcode.netty.im.webserver.base.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;


/**
 * 用户帐号
 *
 * @author Leon
 */
public class UserAccountEntity extends BaseModel {
    private static final long serialVersionUID = 1L;

    /**
     * 帐号
     */
    private String account;
    /**
     * 密码
     */
    @JsonIgnore
    private String password;
    /**
     * 禁用状态（0 启用  1 禁用）
     */
    private Integer disableState;
    /**
     * 是否删除（0未删除1已删除）
     */
    private Integer isDel;

    /**
     * 用户基本信息
     */
    private UserInfoEntity userInfo;

    /**
     * 设置：帐号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取：帐号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置：密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取：密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置：禁用状态（0 启用  1 禁用）
     */
    public void setDisableState(Integer disableState) {
        this.disableState = disableState;
    }

    /**
     * 获取：禁用状态（0 启用  1 禁用）
     */
    public Integer getDisableState() {
        return disableState;
    }

    /**
     * 设置：是否删除（0未删除1已删除）
     */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**
     * 获取：是否删除（0未删除1已删除）
     */
    public Integer getIsDel() {
        return isDel;
    }


    public UserInfoEntity getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoEntity userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
