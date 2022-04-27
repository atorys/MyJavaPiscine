package edu.school21.repositories;

import edu.school21.models.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private final JdbcTemplate jdbcTemplateObject;

    final String UPDATE_QUERY = "UPDATE products SET name = ?, price = ? WHERE identifier = ?";
    final String FIND_ID_QUERY = "SELECT * FROM products WHERE identifier = ";
    final String FIND_ALL_QUERY = "SELECT * FROM products";
    final String SAVE_QUERY = "INSERT INTO products (name, price) VALUES (?, ?)";
    final String DELETE_QUERY = "DELETE FROM products WHERE identifier = ?";

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    private static final class ProductMapper implements RowMapper<Product> {
        public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return (new Product(resultSet.getLong("identifier"),
                                resultSet.getString("name"),
                                resultSet.getInt("price")));
        }
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplateObject.query(FIND_ALL_QUERY, new ProductMapper());
    }

    @Override
    public Optional<Product> findById(Long id) {
        Product product = jdbcTemplateObject.queryForObject(FIND_ID_QUERY + id.intValue(), new ProductMapper());
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
