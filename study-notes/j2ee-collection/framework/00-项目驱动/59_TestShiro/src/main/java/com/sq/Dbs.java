package com.sq;

import java.util.List;

public class Dbs {
    public static SysUser get(String username) {
        if ("sq666".equals(username)) {
            SysUser user = new SysUser();
            user.setUsername("sq666");
            user.setPassword("sq666");
            return user;
        } else if ("sq333".equals(username)) {
            SysUser user = new SysUser();
            user.setUsername("sq333");
            user.setPassword("sq333");
            return user;
        }
        return null;
    }

    public static List<String> getRoles(String username) {
        if ("sq666".equals(username)) {
            return List.of("admin");
        } else if ("sq333".equals(username)) {
            return List.of("normal");
        }
        return null;
    }

    public static List<String> getPermissions(String username) {
        if ("sq666".equals(username)) {
            return List.of("user:create", "user:update", "user:delete");
        } else if ("sq333".equals(username)) {
            return List.of("user:read");
        }
        return null;
    }
}
