package com.nisum.customer.repository;
import com.nisum.customer.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testFindByEmailContaining() {
        customerRepository.save(new Customer(null, "Alice", "alice@mail.com", LocalDate.now()));
        List<Customer> result = customerRepository.findByEmailContaining("mail");
        assertThat(result).hasSize(1);
    }

    @Test
    void testFindByRegisteredDateAfter() {
        customerRepository.save(new Customer(null, "Bob", "bob@mail.com", LocalDate.now().minusDays(10)));
        customerRepository.save(new Customer(null, "Charlie", "charlie@mail.com", LocalDate.now()));
        List<Customer> result = customerRepository.findByRegisteredDateAfter(LocalDate.now().minusDays(5));
        assertThat(result).hasSize(1);
    }

    @Test
    void testFindByNameQuery() {
        customerRepository.save(new Customer(null, "David", "david@mail.com", LocalDate.now()));
        List<Customer> result = customerRepository.findByName("David");
        assertThat(result).hasSize(1);
    }
}

