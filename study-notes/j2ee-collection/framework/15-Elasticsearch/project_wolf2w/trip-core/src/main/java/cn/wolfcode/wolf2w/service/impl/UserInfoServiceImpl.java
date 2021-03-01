package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.exception.LogicException;
import cn.wolfcode.wolf2w.mapper.UserInfoMapper;
import cn.wolfcode.wolf2w.redis.service.IUserInfoRedisService;
import cn.wolfcode.wolf2w.service.IUserInfoService;
import cn.wolfcode.wolf2w.util.AssertUtil;
import cn.wolfcode.wolf2w.util.Consts;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {


    @Autowired
    private IUserInfoRedisService userInfoRedisService;



    public boolean checkPhone(String phone) {
        //select count(id) from userinfo where phone = phone
        QueryWrapper<UserInfo> wrapper = Wrappers.<UserInfo>query().eq("phone", phone);
        int count = super.count(wrapper);
        return count > 0;
    }


    //@Value("${url}")
    //private String url;


    @Override
    public void sendVerifyCode(String phone) {
        //创建验证码
        String code = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 4);
        //拼接短信， 并发送短信
        StringBuilder sb = new StringBuilder(100);
        sb.append("您的验证码是：").append(code).append("请在")
                .append(Consts.VERIFY_CODE_VAI_TIME).append("分钟内使用!");
        //假装发送
        System.out.println(sb);
        //使用java 发起http请求
        //1:url
       /* String url = "https://way.jd.com/chuangxin/cxchangsms?mobile={1}" +
                "&content=【创信】亲，包包已插上幸福的翅膀奔向您！单号：{2}，登录手机客户端随时关注包包行程哟！" +
                "&appkey={3}";
        String appkey = "dd1f7d99cd632060789a56cfaa3b77ce";
        //Spring以restful风格发送发起http请求操作工具类：RestTemplate
        RestTemplate template = new RestTemplate();
        String ret = template.getForObject(url, String.class, phone, code, appkey);
        System.out.println(ret);
        //Success
        if(!ret.toUpperCase().contains("SUCCESS")){
            throw new LogicException("短信发送失败");
        }*/
        //将验证码缓存redis中
        userInfoRedisService.setVerifyCode(phone, code);
    }


    @Override
    public void regist(String phone, String nickname, String password, String rpassword, String verifyCode) {
        //判断参数是否为null
        AssertUtil.hasLength(phone, "手机号码不能为空");
        AssertUtil.hasLength(nickname, "昵称不能为空");
        AssertUtil.hasLength(password, "密码不能为空");
        AssertUtil.hasLength(rpassword, "确认密码不能为空");
        AssertUtil.hasLength(verifyCode, "验证码不能为空");
        //判断手机格式是否正确 @TODO
        //判断手机号码是否唯一
        if (this.checkPhone(phone)) {
            throw new LogicException("手机号码已经被注册了");
        }
        //判断2次密码是否一致
        AssertUtil.isEquals(password, rpassword, "2次密码必须一致");
        //判断验证码是否一致 code1 code2
        String code1 = userInfoRedisService.getVerifyCode(phone);
        if(!verifyCode.equalsIgnoreCase(code1)){
            throw  new LogicException("验证码时效或者验证错误");
        }
        //用户对象保存
        UserInfo userInfo = new UserInfo();
        userInfo.setNickname(nickname);
        userInfo.setPassword(password);  //假装加密
        userInfo.setPhone(phone);
        userInfo.setHeadImgUrl("/images/default.jpg");
        userInfo.setState(UserInfo.STATE_NORMAL);
        super.save(userInfo);
    }

    @Override
    public UserInfo login(String username, String password) {
        QueryWrapper<UserInfo> wrapper = Wrappers.<UserInfo>query()
                .eq("phone", username)
                .eq("password", password);
        UserInfo user = super.getOne(wrapper);
        if(user == null){
            throw  new LogicException("账号或密码错误");
        }
        return user;
    }


    @Override
    public List<UserInfo> queryByCity(String city) {
        QueryWrapper<UserInfo> wrapper = Wrappers.<UserInfo>query()
                .eq("city", city);
        return super.list(wrapper);
    }
}
