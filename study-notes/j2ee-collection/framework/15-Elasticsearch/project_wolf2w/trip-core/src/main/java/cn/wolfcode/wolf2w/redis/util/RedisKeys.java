package cn.wolfcode.wolf2w.redis.util;

import cn.wolfcode.wolf2w.util.Consts;
import lombok.Getter;

/**
 * redis key 管理类
 * 约定：一个redis key对应一个枚举实例
 */
@Getter
public enum  RedisKeys {
    //接口防刷实例
    BRUSH_PROOF("brush_proof", 60L),

    //用户攻略点赞对象 key实例对象
    USER_STRATEGY_THUMB("user_strategy_thumb", -1L),

    //用户攻略收藏对象 key实例对象
    USER_STRATEGY_FAVOR("user_strategy_favor", -1L),


    //攻略统计对象 key实例对象
    STRATEGY_STATIS_VO("strategy_statis_vo", -1L),

    //用户登录token key实例对象
    USER_LOGIN_TOKEN("user_login_token", Consts.USER_INFO_TOKEN_VAI_TIME * 60L),


    //注册验证码key实例对象
    REGIST_VERIFY_CODE("regist_verify_code", Consts.VERIFY_CODE_VAI_TIME * 60L);

    private String prefix;  //key 前缀
    private Long time;   //key 有效时间， 单位是 s
    private RedisKeys(String prefix, Long time){
        this.prefix = prefix;
        this.time = time;
    }
    //拼接完整的key
    public String join(String...values){
        StringBuilder sb = new StringBuilder(80);
        sb.append(this.prefix);
        for (String value : values) {
            sb.append(":").append(value);
        }
        return  sb.toString();
    }
    public static void main(String[] args) {
        //regist_verify_code:13700000000:xxxx:yyy:zzzz
        String key = RedisKeys.REGIST_VERIFY_CODE.join("13700000000", "xxxx","yyyy", "zzzz");
        System.out.println(key);
    }
}
