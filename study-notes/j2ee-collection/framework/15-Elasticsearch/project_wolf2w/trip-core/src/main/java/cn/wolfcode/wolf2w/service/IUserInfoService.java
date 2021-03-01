package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户服务接口
 */
public interface IUserInfoService extends IService<UserInfo> {

    /**
     * 检查手机是否存在
     * @param phone
     * @return true：手机号码存在，  false：手机号码不存在
     */
    boolean checkPhone(String phone);

    /**
     * 发送短信验证码
     * @param phone
     */
    void sendVerifyCode(String phone);

    /**
     * 用户注册
     * @param phone
     * @param nickname
     * @param password
     * @param rpassword
     * @param verifyCode
     */
    void regist(String phone, String nickname, String password, String rpassword, String verifyCode);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    UserInfo login(String username, String password);

    /**
     * 查询某个城市下用户
     * @param city
     * @return
     */
    List<UserInfo> queryByCity(String city);
}
