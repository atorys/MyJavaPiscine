package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;



public class ProductsReposutoryJdbcImplTest {

    private ProductsRepositoryJdbcImpl productsRepositoryJdbc;
    private EmbeddedDatabase database;
    private final List<Product> EXPECTED_FIND_ALL_PRODUCTS =  Arrays.asList(new Product(0L, "happiness", 10000),
                                                                            new Product(1L, "kitty", 0),
                                                                            new Product(2L, "education", 50),
                                                                            new Product(3L, "apple", 15),
                                                                            new Product(4L, "burger", 200));
    final Product EXPECTED_FIND_BY_ID_PRODUCT = EXPECTED_FIND_ALL_PRODUCTS.get(0);
    final Product EXPECTED_UPDATED_PRODUCT = EXPECTED_FIND_ALL_PRODUCTS.get(3);
    final Product EXPECTED_SAVE_PRODUCT = new Product(5L, "mayo", 300);

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
    }

    @AfterEach
    void stop() {
        database.shutdown();
    }

    @Test
    void whenFindingCustomerById_thenCorrect() {
        Assertions.assertEquals(Optional.class, productsRepositoryJdbc.findById(1L).getClass());
    }

    @Test
    void whenFindingAllCustomers_thenCorrect() {
        Assertions.assertEquals(ArrayList.class, productsRepositoryJdbc.findAll().getClass());
    }

    @Test
    void findAll_ShouldFindAll() {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepositoryJdbc.findAll());
    }

    @Test
    void findByID_ShouldThrowExceptionWhenIllegalId() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            productsRepositoryJdbc.findById(100000L);
        });
    }

    @Test
    void findByID_ShouldReturnExpectedOptional() {
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, productsRepositoryJdbc.findById(0L).get());
    }

    @Test
    void update_ShouldBeFoundInDataSource() {
        EXPECTED_UPDATED_PRODUCT.setName("IM UPDATED");
        productsRepositoryJdbc.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, productsRepositoryJdbc.findById(3L).get());
    }

    @Test
    void save_ShouldBeFoundInDataSource() {
        productsRepositoryJdbc.save(EXPECTED_SAVE_PRODUCT);
        Assertions.assertEquals(EXPECTED_SAVE_PRODUCT, productsRepositoryJdbc.findById(5L).get());
    }

    @Test
    void delete_ShouldNotBeFoundInDataSource() {
        productsRepositoryJdbc.delete(3L);
        Assertions.assertThrows(RuntimeException.class, () -> {
            productsRepositoryJdbc.findById(3L);
        });
    }
}
