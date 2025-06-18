package com.nisum.customer.repository;
import com.nisum.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByEmailContaining(String keyword);

    List<Customer> findByRegisteredDateAfter(LocalDate date);

    @Query("SELECT c FROM Customer c WHERE c.name = :name")
    List<Customer> findByName(String name);
}
