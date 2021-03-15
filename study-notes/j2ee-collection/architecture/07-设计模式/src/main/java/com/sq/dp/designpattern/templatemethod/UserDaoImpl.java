package com.sq.dp.designpattern.templatemethod;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 具体子类
 */
public class UserDaoImpl extends JdbcTemplate<User> implements IBaseDao<User> {
    @Override
    public void save(User obj) {
        update("insert sql", obj.getId());
    }

    @Override
    public void update(User obj) {
        update("update sql", obj.getId());
    }

    @Override
    public void delete(Long id) {
        update("delete sql", id);
    }

    @Override
    public User get(Long id) {
        List<User> list = query("query sql", id);
        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<User> list() {
        return query("query sql");
    }

    @Override
    public List<User> processRow(ResultSet rs) {
        List<User> ret = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                ret.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}
