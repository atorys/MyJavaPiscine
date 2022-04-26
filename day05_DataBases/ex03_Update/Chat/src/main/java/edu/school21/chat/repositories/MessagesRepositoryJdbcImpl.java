package edu.school21.chat.repositories;

import edu.school21.chat.app.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private final java.sql.Connection connection;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    @Override
    public void save(Message message) throws NotSavedSubEntityException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO Chat.messages (author_id, room_id, text)" +
                                "VALUES (" + message.getAuthor().getId() +
                                ", " + message.getRoom().getId() + ", " +
                                '\'' + message.getText() + '\'' + ");", Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (!resultSet.next())
                return;

            message.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            throw new NotSavedSubEntityException();
        }
    }

    @Override
    public void update(Message message) throws NotSavedSubEntityException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("UPDATE Chat.messages " +
                    "SET author_id = " + message.getAuthor().getId() +
                    ", room_id = " + message.getRoom().getId() +
                    ", text = '" + message.getText() + '\'' +
                    " WHERE id = " + message.getId() + ";");
        } catch (SQLException e) {
            throw new NotSavedSubEntityException();
        }
    }

    @Override
    public Optional<Message> findById(Long id) {
        Optional<Message> msg = Optional.empty();
        User user;
        Chatroom room;
        String text;
        Timestamp time;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Chat.messages WHERE id = " + id);
            if (!resultSet.next())
                return Optional.of(new Message(0, null, null, "no such message", null));
            text = resultSet.getString("text");
            time = resultSet.getTimestamp("time");

            int author_id = resultSet.getInt("author_id");
            int room_id = resultSet.getInt("room_id");
            resultSet = statement.executeQuery("SELECT * FROM Chat.users WHERE id = " + author_id);
            resultSet.next();
            user = new User(author_id, resultSet.getString("login"),
                    resultSet.getString("password"), null, null);

            resultSet = statement.executeQuery("SELECT * FROM Chat.rooms WHERE id = " + room_id);
            resultSet.next();
            room = new Chatroom(room_id, resultSet.getString("name"),
                    null, null);
            msg = Optional.of(new Message(id.intValue(), user, room, text, time));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return msg;
    }
}
