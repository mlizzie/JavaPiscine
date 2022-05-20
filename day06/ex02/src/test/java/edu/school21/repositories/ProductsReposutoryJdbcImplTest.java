package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ProductsReposutoryJdbcImplTest {

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(0, "TTT", 12),
            new Product(1, "Q", 4),
            new Product(2, "daafda", 2),
            new Product(3, "dahu", 20),
            new Product(4, "dUK", 78)
    );
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(1, "Q", 4);
    final Product EXPECTED_UPDATED_PRODUCT =  new Product(3, "daafda", 2);
    final Product EXPECTED_SAVED_PRODUCT = new Product(6, "chaaa", 400);

    private EmbeddedDatabase dataSource;
    private  ProductsRepository productsRepository;


    @BeforeEach
    void init(){
        dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        productsRepository = new ProductsReposutoryJdbcImpl (dataSource);
    }

    @Test
    void testFindAll() throws SQLException {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepository.findAll());
    }

    @Test
    void testFindById() throws SQLException {
        Assertions.assertEquals(productsRepository.findById(1).get(), EXPECTED_FIND_BY_ID_PRODUCT);
    }

    @Test
    void testUpdate() throws SQLException {
        productsRepository.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(productsRepository.findById(3).get(), EXPECTED_UPDATED_PRODUCT);
    }

    @Test
    void testSave() throws SQLException {
        productsRepository.save(EXPECTED_SAVED_PRODUCT);
        Assertions.assertEquals(productsRepository.findById(6).get(), EXPECTED_SAVED_PRODUCT);
    }

    @Test
    void testDelete() throws SQLException {
        productsRepository.delete(1);
        Assertions.assertThrows(RuntimeException.class, () -> productsRepository.findById(1));
    }


    @AfterEach
    void close(){
        dataSource.shutdown();
    }


}
