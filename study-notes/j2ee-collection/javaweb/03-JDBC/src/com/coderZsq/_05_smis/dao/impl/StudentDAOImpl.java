package com.coderZsq._05_smis.dao.impl;

import com.coderZsq._05_smis.dao.IStudentDAO;
import com.coderZsq._05_smis.domain.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements IStudentDAO {
    @Override
    public void save(Student stu) {
        StringBuilder sb = new StringBuilder(80);
        sb.append("INSERT INTO t_student (name, age) VALUES ('");
        sb.append(stu.getName());
        sb.append("',");
        sb.append(stu.getAge());
        sb.append(")");
        //声明资源
        Connection conn = null;
        Statement st = null;
        try {
            // 1: 加载注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2: 获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql:///jdbc", "root", "root");
            // 3: 创建语句对象
            st = conn.createStatement();
            // 4: 执行SQL
            st.executeUpdate(sb.toString());
            // -------------------------------
            // -------------------------------
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5: 释放资源
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {

            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {

                }
            }
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM t_student WHERE id = " + id;

        //声明资源
        Connection conn = null;
        Statement st = null;
        try {
            // 1: 加载注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2: 获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql:///jdbc", "root", "root");
            // 3: 创建语句对象
            st = conn.createStatement();
            // 4: 执行SQL
            st.executeUpdate(sql);
            // -------------------------------
            // -------------------------------
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5: 释放资源
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {

            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {

                }
            }
        }
    }

    @Override
    // UPDATE t_student SET name = 'xx', age = 12 WHERE id = 123
    public void update(Long id, Student newStu) {
        StringBuilder sb = new StringBuilder(80);
        sb.append("UPDATE t_student SET name = '").append(newStu.getName()).append("', age = ").append(newStu.getAge()).append(" WHERE id = ").append(id);

        //声明资源
        Connection conn = null;
        Statement st = null;
        try {
            // 1: 加载注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2: 获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql:///jdbc", "root", "root");
            // 3: 创建语句对象
            st = conn.createStatement();
            // 4: 执行SQL
            st.executeUpdate(sb.toString());
            // -------------------------------
            // -------------------------------
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5: 释放资源
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {

            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {

                }
            }
        }
    }

    @Override
    public Student get(Long id) {
        String sql = "SELECT * FROM t_student WHERE id = " + id;
        //声明资源
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // 1: 加载注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2: 获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql:///jdbc", "root", "root");
            // 3: 创建语句对象
            st = conn.createStatement();
            // 4: 执行SQL
            rs = st.executeQuery(sql);
            // -------------------------------
            // 处理结果集
            if (rs.next()) {
                Student stu = new Student();
                // 获取当前光标所在行的id列的值, 并设置到stu对象中
                stu.setId(rs.getLong("id"));
                stu.setName(rs.getString("name"));
                stu.setAge(rs.getInt("age"));
                return stu;
            }
            // -------------------------------
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5: 释放资源
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {

            } finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                } catch (Exception e) {

                } finally {
                    try {
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (Exception e) {

                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<Student> listAll() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM t_student";
        //声明资源
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // 1: 加载注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2: 获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql:///jdbc", "root", "root");
            // 3: 创建语句对象
            st = conn.createStatement();
            // 4: 执行SQL
            rs = st.executeQuery(sql);
            // -------------------------------
            // 处理结果集
            while (rs.next()) {
                Student stu = new Student();
                // 获取当前光标所在行的id列的值, 并设置到stu对象中
                stu.setId(rs.getLong("id"));
                stu.setName(rs.getString("name"));
                stu.setAge(rs.getInt("age"));
                list.add(stu); // 把学生对象先存储到List集合中
            }
            // -------------------------------
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5: 释放资源
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {

            } finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                } catch (Exception e) {

                } finally {
                    try {
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (Exception e) {

                    }
                }
            }
        }
        return list;
    }
}
