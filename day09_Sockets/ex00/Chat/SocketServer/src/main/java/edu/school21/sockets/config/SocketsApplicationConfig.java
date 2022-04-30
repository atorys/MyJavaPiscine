package edu.school21.sockets.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.sockets.repositories.UsersRepository;
import edu.school21.sockets.repositories.UsersRepositoryImpl;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SocketsApplicationConfig {

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig("/db.properties");
        return new HikariDataSource(config);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UsersRepository usersRepository() {
        return new UsersRepositoryImpl(dataSource());
    }

    @Bean
    public UsersService usersService() {
        return new UsersServiceImpl(usersRepository(), encoder());
    }

//    @Bean
//    public UsersRepositoryImpl usersRepository() {
//        return new UsersRepositoryImpl();
//    }

}
