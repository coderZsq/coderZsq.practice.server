package com.seemygo.shop.cloud.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wolfcode-lanxw
 */
public class CookieUtil {

    public static final String TOKEN_IN_COOKIE = "userToken";
    private static final String DEFAULT_COOKIE_DOMAIN = "localhost";
    private static final String DEFAULT_COOKIE_PATH = "/";
    private static final Integer DEFAULT_COOKIE_MAX_AGE = 1800;

    public static void addCookie(String cookieName, String cookieValue, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setDomain(DEFAULT_COOKIE_DOMAIN);
        cookie.setPath(DEFAULT_COOKIE_PATH);
        cookie.setMaxAge(DEFAULT_COOKIE_MAX_AGE);// cookie存活時間：單位/秒
        response.addCookie(cookie);
    }

    public static String getCookieValue(String cookieName, HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (CookieUtil.TOKEN_IN_COOKIE.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }
}
