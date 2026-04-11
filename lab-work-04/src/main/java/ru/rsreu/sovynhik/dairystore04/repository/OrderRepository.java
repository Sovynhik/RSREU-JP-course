package ru.rsreu.sovynhik.dairystore04.repository;

import ru.rsreu.sovynhik.dairystore04.document.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}