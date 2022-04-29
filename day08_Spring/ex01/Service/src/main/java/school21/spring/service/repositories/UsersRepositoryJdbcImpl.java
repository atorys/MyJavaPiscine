package school21.spring.service.repositories;

import school21.spring.service.models.User;


import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private final java.sql.Connection connection;
    final String UPDATE_QUERY = "UPDATE Service.users SET email = ? WHERE id = ?";
    final String FIND_ID_QUERY = "SELECT * FROM Service.users WHERE id = ";
    final String FIND_ALL_QUERY = "SELECT * FROM Service.users";
    final String FIND_EMAIL_QUERY = "SELECT * FROM Service.users WHERE email = ?";
    final String SAVE_QUERY = "INSERT INTO Service.users (email) VALUES (?)";
    final String DELETE_QUERY = "DELETE FROM Service.users WHERE id = ?";

    public UsersRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    @Override
    public User findById(Long id) {
        User user = null;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ID_QUERY + id);
            if (!resultSet.next())
                return null;
            user = new User(id, resultSet.getString("email"));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        try (Statement statement = connection.createStatement())
        {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY);
            while(resultSet.next())
                list.add(new User(resultSet.getLong("id"), resultSet.getString("email")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void save(User entity) {
        try (PreparedStatement statement = connection.prepareStatement(SAVE_QUERY)) {
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getEmail());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, entity.getEmail());
            statement.setLong(2, entity.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_EMAIL_QUERY)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next())
                return Optional.empty();
            user = new User(resultSet.getLong(1), resultSet.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user == null)
            return Optional.empty();
        return Optional.of(user);
    }
}
