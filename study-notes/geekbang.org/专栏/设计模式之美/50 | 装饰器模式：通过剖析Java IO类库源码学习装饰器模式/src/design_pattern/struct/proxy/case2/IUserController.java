package design_pattern.struct.proxy.case2;

import object_oriented.ddd.case1.UserVo;

public interface IUserController {
    UserVo login(String telephone, String password);
    UserVo register(String telephone, String password);
}