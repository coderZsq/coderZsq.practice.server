package cn.wolfcode.wolf2w.resolver;


import cn.wolfcode.wolf2w.annotation.UserParam;
import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.redis.service.IUserInfoRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 将请求映射方法列表中UserInfo类型参数进行解析
 * 解析成当前登录用户对象
 */
public class UserInfoArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private IUserInfoRedisService userInfoRedisService;
    //判断当前解析器支持解析的参数类型，返回true表示支持意思
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType() == UserInfo.class
                && methodParameter.hasParameterAnnotation(UserParam.class)
                ;
    }
    //解析器解析规则：
    //此处将UserInfo类型参数， 解析成当前登录用户对象。
    //当supportsParameter方法返回true时候才执行
    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String token = request.getHeader("token");
        return userInfoRedisService.getUserByToken(token);
    }
}
