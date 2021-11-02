package reconfiguration.sepc.case2;

public interface Course {
    public void buyCourse(long userId, long courseId, boolean isVip);

    // 将其拆分成两个函数
    public void buyCourse(long userId, long courseId);
    public void buyCourseForVip(long userId, long courseId);


    // // 拆分成两个函数的调用方式
    // boolean isVip = false;
    // //...省略其他逻辑...
    // if (isVip) {
    //     buyCourseForVip(userId, courseId);
    // } else {
    //     buyCourse(userId, courseId);
    // }
    //
    // // 保留标识参数的调用方式更加简洁
    // boolean isVip = false;
    // //...省略其他逻辑...
    // buyCourse(userId, courseId, isVip);
}
