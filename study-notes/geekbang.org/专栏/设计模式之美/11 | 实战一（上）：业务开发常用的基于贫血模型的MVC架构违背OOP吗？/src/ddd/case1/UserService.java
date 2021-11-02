package ddd.case1;

////////// Service+BO(Business Object) //////////
public class UserService {
    private UserRepository userRepository; //通过构造函数或者IOC框架注入

    public UserBo getUserById(Long userId) {
        UserEntity userEntity = userRepository.getUserById(userId);
        //[...convert userEntity to userBo...];
        UserBo userBo = new UserBo();
        userBo.setId(userEntity.getId());
        userBo.setName(userEntity.getName());
        userBo.setCellphone(userEntity.getCellphone());
        return userBo;
    }
}
