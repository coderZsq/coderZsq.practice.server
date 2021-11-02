package ddd.case1;


////////// Controller+VO(View Object) //////////
public class UserController {
    private UserService userService; //通过构造函数或者IOC框架注入

    public UserVo getUserById(Long userId) {
        UserBo userBo = userService.getUserById(userId);
        // [...convert userBo to userVo...];
        UserVo userVo = new UserVo();
        userVo.setId(userBo.getId());
        userVo.setName(userBo.getName());
        userVo.setCellphone(userBo.getCellphone());
        return userVo;
    }
}









