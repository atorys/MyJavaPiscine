package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersServiceImpl implements UsersRepository {

    private JdbcTemplate jdbcTemplateObject;

    final String UPDATE_QUERY = "UPDATE users SET login = ?, password = ?, status = ? WHERE identifier = ?";
    final String FIND_LOGIN_QUERY = "SELECT * FROM users WHERE login = ";

    public UsersServiceImpl(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    private static final class UserMapper implements RowMapper<User> {
        public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return (new User(resultSet.getLong("identifier"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getBoolean("status")));
        }
    }

    public JdbcTemplate getJdbcTemplateObject() {
        return jdbcTemplateObject;
    }

    public void setJdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {
        this.jdbcTemplateObject = jdbcTemplateObject;
    }

    @Override
    public User findByLogin(String login) throws EntityNotFoundException {
        User user = jdbcTemplateObject.queryForObject(FIND_LOGIN_QUERY + login, new UserMapper());
        if (user == null)
            throw new EntityNotFoundException();
        return user;
    }

    @Override
    public void update(User user) throws EntityNotFoundException {
        int result = jdbcTemplateObject.update(UPDATE_QUERY,
                user.getLogin(),
                user.getPassword(),
                user.isStatus(),
                user.getIdentifier().intValue());
        if (result == 0)
            throw new EntityNotFoundException();
    }

    public boolean authenticate(String login, String password) {
        User user = findByLogin(login);
        if (user.isStatus()) {
            throw new AlreadyAuthenticatedException();
        } else if (user.getPassword().equals(password)) {
            user.setStatus(true);
            update(user);
        }
        return user.isStatus();
    }
}
