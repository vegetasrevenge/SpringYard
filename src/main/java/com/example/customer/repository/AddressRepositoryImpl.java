package com.example.customer.repository;

import com.example.customer.model.Address;
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
public class AddressRepositoryImpl implements AddressRepository {

    private static final Logger log = LoggerFactory.getLogger(com.example.customer.repository.AddressRepository.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void add(Address address) {
        log.info("adding " + address);
        jdbcTemplate.update("INSERT INTO address(street, city, state, zip, customer_id) VALUES (?,?,?,?,?)",
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZip(),
                address.getCustomerId());
    }

    @Override
    public Address findByCustomerId(int id) {
        log.info("findByPersonId " + id);
        List<Address> addresses = jdbcTemplate.query("SELECT * FROM address WHERE customer_id = ?", new AddressMapper(), id);

        if (addresses.size() > 0) {
            return addresses.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void update(Address address) {
        log.info("updating " + address);
        jdbcTemplate.update("UPDATE address SET street=?, state=?, zip=?, where id=?",
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZip(),
                address.getId());
    }

    @Override
    public void delete(int id) {
        log.info("delete " + id);
        jdbcTemplate.update("DELETE FROM address WHERE customer_id = ?", id);
    }

    @Override
    public void deleteByCustomerId(int id) {
        log.info("delete " + id);
        jdbcTemplate.update("DELETE FROM address WHERE customer_id = ?", id);
    }


    private static class AddressMapper implements RowMapper<Address> {
        @Override
        public Address mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Address address = new Address();
            address.setId(resultSet.getInt("id"));
            address.setStreet(resultSet.getString("street"));
            address.setCity(resultSet.getString("city"));
            address.setState(resultSet.getString("state"));
            address.setZip(resultSet.getString("zip"));
            address.setCustomerId(resultSet.getInt("customer_id"));
            return address;
        }
    }
}
