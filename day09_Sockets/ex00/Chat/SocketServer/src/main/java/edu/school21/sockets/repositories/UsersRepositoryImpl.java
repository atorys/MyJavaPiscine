package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryImpl implements UsersRepository {

    private final JdbcTemplate jdbcTemplateObject;
    final String UPDATE_QUERY = "UPDATE Service.users SET login = ?, password = ? WHERE id = ?";
    final String FIND_ID_QUERY = "SELECT * FROM Service.users WHERE id = ?";
    final String FIND_ALL_QUERY = "SELECT * FROM Service.users";
    final String FIND_LOGIN_QUERY = "SELECT * FROM Service.users WHERE login = ?";
    final String SAVE_QUERY = "INSERT INTO Service.users (login, password) VALUES (?, ?)";
    final String DELETE_QUERY = "DELETE FROM Service.users WHERE id = ?";

    @Autowired
    public UsersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    private static final class UserMapper implements RowMapper<User> {
        public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return (new User(resultSet.getLong("id"),
                    resultSet.getString("login"),
                    resultSet.getString("password")));
        }
    }

    @Override
    public User findById(Long id) {
        User user = null;
        try {
            user = jdbcTemplateObject.queryForObject(FIND_ID_QUERY, new UserMapper(), id.intValue());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplateObject.query(FIND_ALL_QUERY, new UserMapper());
    }

    @Override
    public void save(User entity) {
        jdbcTemplateObject.update(SAVE_QUERY, entity.getLogin(), entity.getPassword());
        Optional<User> user = findByLogin(entity.getLogin());
        user.ifPresent(value -> entity.setId(value.getId()));
    }

    @Override
    public void update(User entity) {
        if (jdbcTemplateObject.update(UPDATE_QUERY, entity.getLogin(), entity.getId()) == 0)
            save(entity);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplateObject.update(DELETE_QUERY, id.intValue());
    }

    @Override
    public Optional<User> findByLogin(String login) {
        User user;
        try {
            user = jdbcTemplateObject.queryForObject(FIND_LOGIN_QUERY, new UserMapper(), login);
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }
        if (user != null) {
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }
}
