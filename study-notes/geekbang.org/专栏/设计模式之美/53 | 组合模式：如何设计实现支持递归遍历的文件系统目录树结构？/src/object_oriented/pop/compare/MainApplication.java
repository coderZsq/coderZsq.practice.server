package object_oriented.pop.compare;

public class MainApplication {
    public static void main(String[] args) {
        UserFileFormatter userFileFormatter = new UserFileFormatter();
        userFileFormatter.format("/home/zheng/users.txt", "/home/zheng/formatted_users.txt");
    }
}