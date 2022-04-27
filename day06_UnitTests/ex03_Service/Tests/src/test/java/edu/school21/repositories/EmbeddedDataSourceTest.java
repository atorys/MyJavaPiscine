package edu.school21.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.sql.SQLException;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;



public class EmbeddedDataSourceTest {

    private EmbeddedDatabase db;

    @BeforeEach
    void init() {
        db = new EmbeddedDatabaseBuilder()
                .setName("My Test DB")
                .setType(HSQL)
                .setScriptEncoding("UTF-8")
                .addScript("/schema.sql")
                .addScript("/data.sql")
                .build();
    }

    @AfterEach
    void stop() {
        db.shutdown();
    }

    @Test
    void testConnection() throws SQLException {
        Assertions.assertNotNull(db.getConnection());
    }
}
