package school21.spring.service.repositories;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import school21.spring.service.models.User;

import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private  JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM service.users Where email = ?",new Object[]{email} ,new BeanPropertyRowMapper<>(User.class))
                .stream().findAny();
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM service.users Where id = ?",new Object[]{id} ,new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM service.users", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(User entity) {
                jdbcTemplate.update("INSERT INTO service.users (email) VALUES(?)",entity.getPassword());

    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("UPDATE  service.users SET email = ? WHERE id = ?", entity.getEmail(), entity.getIdentifier());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM service.users WHERE id = ?", id);
    }
}
