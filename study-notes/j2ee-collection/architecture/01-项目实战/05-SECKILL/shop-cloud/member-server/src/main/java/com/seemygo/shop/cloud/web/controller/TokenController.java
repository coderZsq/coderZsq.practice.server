package com.seemygo.shop.cloud.web.controller;

import com.seemygo.shop.cloud.domain.User;
import com.seemygo.shop.cloud.resp.Result;
import com.seemygo.shop.cloud.service.IUserService;
import com.seemygo.shop.cloud.util.CookieUtil;
import com.seemygo.shop.cloud.util.DBUtil;
import com.seemygo.shop.cloud.util.MD5Util;
import com.seemygo.shop.cloud.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/tokens")
public class TokenController {

    private final IUserService userService;

    @Autowired
    public TokenController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result<String> login(@Valid LoginVo loginVo, HttpServletResponse resp) {
        String token = userService.login(loginVo);
        // 将 token 通过响应，添加到 cookie
        CookieUtil.addCookie(resp, CookieUtil.TOKEN_IN_COOKIE, token, CookieUtil.TOKEN_EXPIRE_TIME);
        return Result.success(token);
    }


    @GetMapping("/session")
    public Result<User> session(@CookieValue(value = CookieUtil.TOKEN_IN_COOKIE, required = false) String token) {
        User user = userService.findByToken(token);
        if (user != null) {
            System.out.println("user:" + user.getId());
        }
        return Result.success(user);
    }

    @RequestMapping("/initData")
    public Result<String> initData() throws Exception {
        List<User> users = initUser();//在内存中创建100个User对象
        //insertToDb(users);//把100个User对象保存到数据库中
        createToken(users);//通过100个User对象，调用service方法，模拟登陆，创建100个token,然后存在磁盘文件中.(jmeter)
        return Result.success("");
    }

    private void createToken(List<User> users) throws Exception {
        File file = new File("D:/tokens.txt");
        if (file.exists()) {
            boolean delete = file.delete();
        }
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            boolean newFile = file.createNewFile();
            raf.seek(0);
            for (User user : users) {
                LoginVo vo = new LoginVo();
                vo.setUsername(user.getId() + "");
                vo.setPassword(MD5Util.inputPass2FormPass("111111"));
                String token = userService.login(vo);
                String row = user.getId() + "," + token;
                raf.seek(raf.length());
                raf.write(row.getBytes());
                raf.write("\r\n".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertToDb(List<User> users) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConn();
            String sql = "insert into t_user(login_count, nickname, register_date, salt, password, id)values(?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            for (User user : users) {
                pstmt.setInt(1, user.getLoginCount());
                pstmt.setString(2, user.getNickname());
                pstmt.setTimestamp(3, new Timestamp(user.getRegisterDate().getTime()));
                pstmt.setString(4, user.getSalt());
                pstmt.setString(5, user.getPassword());
                pstmt.setLong(6, user.getId());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    private List<User> initUser() {
        List<User> users = new ArrayList<>();
        int max = 100;
        for (int i = 0; i < max; i++) {
            User user = new User();
            user.setId(13000000000L + i);
            user.setLoginCount(1);
            user.setNickname("user" + i);
            user.setRegisterDate(new Date());
            user.setSalt("1a2c3d4e");
            user.setPassword(MD5Util.formPass2DbPass(MD5Util.inputPass2FormPass("111111"), user.getSalt()));
            users.add(user);
        }
        return users;
    }
}