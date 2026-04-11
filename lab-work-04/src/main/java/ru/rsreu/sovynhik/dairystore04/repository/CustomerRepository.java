package ru.rsreu.sovynhik.dairystore04.repository;

import ru.rsreu.sovynhik.dairystore04.document.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    Optional<Customer> findByEmail(String email);
}