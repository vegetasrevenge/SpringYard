package com.example.customer.service;

import com.example.customer.repository.CustomerRepository;
import com.example.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String INSERT_SQL = "INSERT INTO customer (firstName, lastName) VALUES (?,?)";
    @Transactional
    @Override
    public void add(Customer customer) {
        jdbcTemplate.update(INSERT_SQL, customer.getFirstName(), customer.getLastName());
    }

    private final String SELECT_BY_ID_SQL = "SELECT * FROM customer WHERE id = ?";
    @Override
    public Customer getById(int id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, new CustomerServiceImpl.CustomerMapper(), id);
    }

    private final String SELECT_SQL = "SELECT * FROM customer";
    @Override
    public List<Customer> get() {
        return jdbcTemplate.query(SELECT_SQL, new CustomerServiceImpl.CustomerMapper());
    }

    private final String UPDATE_SQL = "UPDATE customer SET firstName=?, lastName=? where id=?";
    @Transactional
    @Override
    public void update(Customer customer) {
        jdbcTemplate.update(UPDATE_SQL, customer.getFirstName(), customer.getLastName(), customer.getId());
    }

    private final String DELETE_SQL = "delete from customer where id=?";
    @Transactional
    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }


    private static class CustomerMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("firstName"));
            customer.setLastName(rs.getString("lastName"));
            return customer;
        }
    }
}
