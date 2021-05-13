package cn.wolfcode.netty.im.webserver.base.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Leon
 */
public abstract class BaseModel implements Serializable {
    private static final long serialVersionUID = 2539516018935036745L;

    protected Long id;

    protected Date createDate;

    protected Long createUser;

    protected Date updateDate;

    protected Long updateUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }
}
