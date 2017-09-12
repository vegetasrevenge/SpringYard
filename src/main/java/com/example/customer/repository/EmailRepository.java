package com.example.customer.repository;

import com.example.customer.model.Email;

import java.util.List;

public interface EmailRepository {
    void add(Email email);
    List<Email> findByCustomerId(int id);
    void delete(int id);
    void deleteByCustomerId(int id);
}
