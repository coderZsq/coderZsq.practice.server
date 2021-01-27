package com.sq;

public class Dbs {
    public static SysUser get(String username) {
        if ("sq666".equals(username)) {
            SysUser user = new SysUser();
            user.setUsername("sq666");
            user.setPassword("sq666");
            return user;
        }
        return null;
    }
}
