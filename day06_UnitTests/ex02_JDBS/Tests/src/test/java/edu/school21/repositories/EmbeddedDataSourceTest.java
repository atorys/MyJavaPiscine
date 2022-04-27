package edu.school21.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

import javax.sql.DataSource;
import java.sql.SQLException;



public class EmbeddedDataSourceTest {

    private DataSource db;

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

    @Test
    void testConnection() throws SQLException {
        Assertions.assertNotNull(db.getConnection());
    }
}
