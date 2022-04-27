package edu.school21.services;

import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.ProductsRepositoryJdbcImpl;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import static org.mockito.Mockito.*;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;



public class UsersServiceImplTest {
    private ProductsRepositoryJdbcImpl productsRepositoryJdbc;
    private UsersServiceImpl usersService;
    private EmbeddedDatabase database;

    @BeforeEach
    void init() {
        database = new EmbeddedDatabaseBuilder()
                .setName("My Test DB")
                .setType(HSQL)
                .setScriptEncoding("UTF-8")
                .addScript("/schema.sql")
                .addScript("/data.sql")
                .build();
        productsRepositoryJdbc = new ProductsRepositoryJdbcImpl(database);
        usersService = new UsersServiceImpl(database);
    }

    @AfterEach
    void stop() {
        database.shutdown();
    }


}
