package com.example.customer.repository;

import com.example.customer.model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmailRepositoryImpl implements EmailRepository {

    private static final Logger log = LoggerFactory.getLogger(com.example.customer.repository.EmailRepositoryImpl.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void add(Email email) {
        log.info("adding " + email);
        jdbcTemplate.update("INSERT INTO email(email, customer_id) VALUES (?,?)",
                email.getEmail(),
                email.getCustomerId());
    }

    @Override
    public List<Email> findByCustomerId(int id) {
        log.info("findByCustomerId " + id);
        return jdbcTemplate.query("SELECT * FROM email WHERE customer_id = ?", new EmailMapper(), id);

    }

    @Override
    public void delete(int id) {
        log.info("delete " + id);
        jdbcTemplate.update("DELETE FROM email WHERE id = ?", id);
    }

    @Override
    public void deleteByCustomerId(int id) {
        log.info("delete " + id);
        jdbcTemplate.update("DELETE FROM email WHERE customer_id = ?", id);
    }

    private static class EmailMapper implements RowMapper<Email> {
        @Override
        public Email mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Email email = new Email();
            email.setId(resultSet.getInt("id"));
            email.setEmail(resultSet.getString("email"));
            email.setCustomerId(resultSet.getInt("customer_id"));
            return email;
        }
    }
}
