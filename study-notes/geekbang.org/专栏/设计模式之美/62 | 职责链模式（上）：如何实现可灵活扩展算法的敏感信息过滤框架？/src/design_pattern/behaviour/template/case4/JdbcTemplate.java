package design_pattern.behaviour.template.case4;

import design_pattern.behaviour.template.case3.User;

import java.util.List;

public class JdbcTemplate {
    public List<User> query(String sql, JdbcTemplateDemo.UserRowMapper userRowMapper) {
        return null;
    }
}
