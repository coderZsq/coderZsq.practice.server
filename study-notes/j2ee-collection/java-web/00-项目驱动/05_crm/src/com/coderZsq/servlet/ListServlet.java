package com.coderZsq.servlet;

import com.coderZsq.dao.CustomerDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
    private final CustomerDao dao = new CustomerDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
        requset.setAttribute("customers", dao.list());
        requset.getRequestDispatcher("/page/list.jsp").forward(requset, response);
    }

    // @Override
    // protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //     try {
    //         String sql = "SELECT id, name, age, height FROM customer";
    //         Class.forName("com.mysql.jdbc.Driver");
    //         // 从数据库获取所有的客户数据
    //         try (Connection conn = DriverManager.getConnection(Constants.URL, Constants.USERNAME, Constants.PASSWORD);
    //              PreparedStatement pstmt = conn.prepareStatement(sql);
    //              ResultSet rs = pstmt.executeQuery()) {
    //             List<Customer> customers = new ArrayList<>();
    //             while (rs.next()) {
    //                 Customer customer = new Customer();
    //                 customer.setId(rs.getInt("id"));
    //                 customer.setName(rs.getString("name"));
    //                 customer.setAge(rs.getInt("age"));
    //                 customer.setHeight(rs.getDouble("height"));
    //                 customers.add(customer);
    //             }
    //             // 将客户数据保存到request中
    //             request.setAttribute("customers", customers);
    //
    //             // 转发到对应的jsp页面, 在jsp页面中显示customer数据
    //             request.getRequestDispatcher("/page/list.jsp").forward(request, response);
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }
}
