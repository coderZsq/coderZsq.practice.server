package com.seemygo.shop.cloud.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    public static final String TOKEN_IN_COOKIE = "userToken";
    public static final int TOKEN_EXPIRE_TIME = 1800;

    public static void addCookie(HttpServletResponse resp, String cookieName, String cookieValue, Integer expireSeconds) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setMaxAge(expireSeconds);// 单位是秒
        resp.addCookie(cookie);
    }
}
