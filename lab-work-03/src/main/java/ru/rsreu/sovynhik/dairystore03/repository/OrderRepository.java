package ru.rsreu.sovynhik.dairystore03.repository;

import ru.rsreu.sovynhik.dairystore03.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
