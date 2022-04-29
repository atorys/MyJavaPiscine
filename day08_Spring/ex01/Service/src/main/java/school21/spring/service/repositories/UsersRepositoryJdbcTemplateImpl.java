package school21.spring.service.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private final JdbcTemplate jdbcTemplateObject;
    final String UPDATE_QUERY = "UPDATE Service.users SET email = ? WHERE id = ?";
    final String FIND_ID_QUERY = "SELECT * FROM Service.users WHERE id = ";
    final String FIND_ALL_QUERY = "SELECT * FROM Service.users";
    final String FIND_EMAIL_QUERY = "SELECT * FROM Service.users WHERE email = ?";
    final String SAVE_QUERY = "INSERT INTO Service.users (email) VALUES (?)";
    final String DELETE_QUERY = "DELETE FROM Service.users WHERE id = ?";

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    private static final class UserMapper implements RowMapper<User> {
        public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return (new User(resultSet.getLong("id"),
                    resultSet.getString("email")));
        }
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplateObject.queryForObject(FIND_ID_QUERY + id.intValue(), new UserMapper());
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplateObject.query(FIND_ALL_QUERY, new UserMapper());
    }

    @Override
    public void save(User entity) {
        jdbcTemplateObject.update(SAVE_QUERY, entity.getEmail());
    }

    @Override
    public void update(User entity) {
        int result = jdbcTemplateObject.update(UPDATE_QUERY,
                entity.getEmail(),
                entity.getId());
        if (result == 0)
            save(entity);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplateObject.update(DELETE_QUERY, id.intValue());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = null;
        try {
            user = jdbcTemplateObject.queryForObject(FIND_EMAIL_QUERY, new UserMapper(), email);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return Optional.of(user);
    }
}
