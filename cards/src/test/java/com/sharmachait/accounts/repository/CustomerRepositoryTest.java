package com.sharmachait.accounts.repository;

import com.sharmachait.accounts.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;
    private Customer customer;
    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setName("Test");
        customer.setEmail("test@test.com");
        customer.setMobileNumber("8756876876");
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Test");
        customerRepository.save(customer);
    }
    @Test
    void findByEmail() {
        assertDoesNotThrow(
                ()->customerRepository.findByEmail(
                        customer.getEmail()
                ).orElseThrow(
                        ()->new Exception("Not Found")
                ));
        assertNotNull(customer.getCreatedAt());
        assertNotNull(customer.getCustomerId());
    }

    @Test
    void findByMobileNumber() {
        assertDoesNotThrow(
                ()->customerRepository.findByMobileNumber(
                        customer.getMobileNumber()
                ).orElseThrow(
                        ()->new Exception("Not Found")
                ));
        assertNotNull(customer.getCreatedAt());
        assertNotNull(customer.getCustomerId());
    }
}