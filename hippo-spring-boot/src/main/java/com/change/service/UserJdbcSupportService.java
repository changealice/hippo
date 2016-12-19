package com.change.service;

import com.change.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: change.long
 * Date: 16/5/18
 * Time: 下午3:08
 */
@Service("userJdbcSupportService")
public class UserJdbcSupportService implements IUserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findByUserName(final String userName) {
        final List<User> users = new ArrayList<User>();
        jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement("SELECT * FROM t_user WHERE user_name = ?");
            }
        }, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, userName);
            }
        }, new ResultSetExtractor<User>() {
            @Override
            public User extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setUserName(rs.getString("user_name"));
                    user.setPassword(rs.getString("password"));
                    users.add(user);
                    return user;
                }
                return null;
            }
        });
        return users;
    }

    @Override
    public List<User> findByUserName2(String userName) {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public User findOne(User user) {
        return null;
    }
}
