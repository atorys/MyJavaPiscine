package edu.school21.chat.app;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
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

        User user = new User(1, "i'm author", "1", new LinkedList<>(), new LinkedList<>());
        Chatroom chatroom = new Chatroom(1, "private room", user, new LinkedList<>());
        Message message = new Message(null, user, chatroom, "kto prochital tot sd@xnet", Timestamp.valueOf(LocalDateTime.now()));

        MessagesRepository repositoryJdbc = new MessagesRepositoryJdbcImpl(dataSource);
        repositoryJdbc.save(message);

        System.out.println(message.getId());
        dataSource.close();
    }
}
