package cn.wolfcode.wolf2w.util;

import cn.wolfcode.wolf2w.exception.LogicException;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

/**
 * 参数断言工具类
 */
public class AssertUtil {
    private  AssertUtil(){}
    /**
     * 判断指定text 是否有值， 如果没有值抛出message信息异常
     * @param text
     * @param message
     */
    public static void hasLength(@Nullable String text, String message) {
        if (!StringUtils.hasLength(text)) {
            throw new LogicException(message);
        }
    }


    /**
     * 判断v1v2是否一致， 不一致抛出msg信息
     * @param v1
     * @param v2
     * @param msg
     */
    public static void isEquals(String v1, String v2, String msg) {
        if(v1 == null || v2 == null){
            throw  new RuntimeException("参数不能为null");
        }
        if(!v1.equals(v2)){
            throw  new LogicException(msg);
        }
    }
}
