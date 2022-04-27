package edu.school21.repositories;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import edu.school21.models.Product;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private final JdbcTemplate jdbcTemplateObject;

    final String UPDATE_QUERY = "UPDATE test_table SET name = ?, price = ? WHERE identifier = ?";
    final String FIND_ID_QUERY = "SELECT * FROM test_table WHERE identifier = ";
    final String FIND_ALL_QUERY = "SELECT * FROM test_table";
    final String SAVE_QUERY = "INSERT INTO test_table (name, price) VALUES (?, ?)";
    final String DELETE_QUERY = "DELETE FROM test_table WHERE identifier = ?";

    public ProductsRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplateObject.queryForList(FIND_ALL_QUERY, Product.class);
    }

    @Override
    public Optional<Product> findById(Long id) {
        Product product = jdbcTemplateObject.queryForObject(FIND_ID_QUERY + id.intValue(), Product.class);
        if (product == null)
            throw new RuntimeException("Invalid identifier");
        return Optional.of(product);
    }

    @Override
    public void update(Product product) {
        int result = jdbcTemplateObject.update(UPDATE_QUERY,
                                               product.getName(),
                                               product.getPrice(),
                                               product.getId().intValue());
        if (result == 0)
            save(product);
    }

    @Override
    public void save(Product product) {
        jdbcTemplateObject.update(SAVE_QUERY, product.getName(), product.getPrice());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplateObject.update(DELETE_QUERY, id.intValue());
    }
}
