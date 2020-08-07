package com.seemygo.shop.cloud.util;

import com.seemygo.shop.cloud.exception.BusinessException;
import com.seemygo.shop.cloud.resp.CodeMsg;
import org.springframework.util.StringUtils;

/**
 * 进行参数判断工具类
 */
public class AssertUtil {

    /**
     * 判断参数不能为空
     *
     * @param value
     * @param codeMsg
     */
    public static void notNull(Object value, CodeMsg codeMsg) {
        if (value == null) {
            throw new BusinessException(codeMsg);
        }
    }

    /**
     * 参数是有值判断
     * @param value
     * @param codeMsg
     * @return
     */
    public static void hasLength(String value, CodeMsg codeMsg) {
        if(!StringUtils.hasLength(value)){
            throw new BusinessException(codeMsg);
        }
    }

    /**
     * 断言如果传进来的参数是true就放行，否则抛出异常
     *
     * @param isTrue
     * @param codeMsg
     */
    public static void isTrue(boolean isTrue, CodeMsg codeMsg) {
        if (!isTrue) {
            throw new BusinessException(codeMsg);
        }
    }
}
