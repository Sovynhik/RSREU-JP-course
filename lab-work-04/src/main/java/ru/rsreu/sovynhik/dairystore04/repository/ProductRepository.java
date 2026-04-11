package ru.rsreu.sovynhik.dairystore04.repository;

import ru.rsreu.sovynhik.dairystore04.document.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByName(String name);
}