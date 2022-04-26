package edu.school21.chat.app;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

public class Program {

    public static void main(String[] args) throws SQLException {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        User user = new User(2, "admin", "admin", null, null);
        Chatroom chatroom = new Chatroom(1, "chatik", user, null);

        MessagesRepository repositoryJdbc = new MessagesRepositoryJdbcImpl(dataSource);
        Optional<Message> message = repositoryJdbc.findById(6L);
        if (message.isPresent()) {
            message.get().setAuthor(user);
            message.get().setText("new text ura!");
            repositoryJdbc.update(message.get());
        } else {
            repositoryJdbc.save(new Message(6, user, chatroom, "auto generated message", Timestamp.valueOf(LocalDateTime.now())));
        }
        System.out.println(repositoryJdbc.findById(6L).get());
        dataSource.close();
    }
}
