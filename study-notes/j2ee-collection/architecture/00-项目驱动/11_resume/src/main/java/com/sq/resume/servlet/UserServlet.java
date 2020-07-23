package com.sq.resume.servlet;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.sq.resume.bean.UploadParams;
import com.sq.resume.bean.User;
import com.sq.resume.service.UserService;
import com.sq.resume.util.Uploads;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Properties;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet<User> {

    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("user", service.list().get(0));
        forward(request, response, "admin/user.jsp");
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UploadParams params = Uploads.parseRequest(request);

        // 请求参数转成User
        User user = new User();
        BeanUtils.populate(user, params.getParams());

        // 从Session中拿到邮箱, 密码
        User loginUser = (User) request.getSession().getAttribute("user");
        user.setEmail(loginUser.getEmail());
        user.setPassword(loginUser.getPassword());

        // 处理用户的头像
        FileItem item = params.getFileParam("photoFile");
        user.setPhoto(Uploads.uploadImage(item, request, user.getPhoto()));

        if (service.save(user)) { // 保存成功
            // 重定向到admin
            redirect(request, response, "user/admin");
        } else { // 保存失败
            forwardError(request, response, "个人信息保存失败");
        }
    }

    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 检查验证码
        String captcha = request.getParameter("captcha").toLowerCase();

        // 从Session中取出验证码
        String code = (String) request.getSession().getAttribute("code");
        if (!captcha.equals(code)) {
            forwardError(request, response, "验证码不正确");
            return;
        }

        // 检查用户名, 密码
        User user = new User();
        BeanUtils.populate(user, request.getParameterMap());
        user = ((UserService) service).get(user);
        if (user != null) { // 用户名, 密码正确
            // 登录成功后, 将User对象放入Session中
            request.getSession().setAttribute("user", user);
            redirect(request, response, "user/admin");
        } else { // 用户名, 密码有问题
            forwardError(request, response, "邮箱或密码不正确");
        }
    }

    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 创建Katpcha对象
        DefaultKaptcha dk = new DefaultKaptcha();

        // 验证码的配置
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("kaptcha.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            Config config = new Config(properties);
            dk.setConfig(config);
        }

        // 生成验证码字符串
        String code = dk.createText();

        // 存储到Session中(当这个客户端是首次请求服务器时, 就会创建一个全新的Session)
        HttpSession session = request.getSession();
        session.setAttribute("code", code.toLowerCase());

        // 生成验证码图片
        BufferedImage image = dk.createImage(code);

        // 设置返回数据的格式
        response.setContentType("image/jpeg");

        // 将图片数据写回到客户端
        ImageIO.write(image, "jpg", response.getOutputStream());
    }
}