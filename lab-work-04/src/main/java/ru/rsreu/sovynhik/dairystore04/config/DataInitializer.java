package ru.rsreu.sovynhik.dairystore04.config;

import ru.rsreu.sovynhik.dairystore04.document.Product;
import ru.rsreu.sovynhik.dairystore04.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;

    public DataInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        if (productRepository.count() == 0) {
            productRepository.save(new Product("Молоко 3,2%", new BigDecimal("65"), 100));
            productRepository.save(new Product("Сыр Гауда", new BigDecimal("480"), 50));
            productRepository.save(new Product("Йогурт клубничный", new BigDecimal("42"), 200));
            productRepository.save(new Product("Творог 5%", new BigDecimal("120"), 75));
            System.out.println("Коллекция products заполнена начальными данными (MongoDB).");
        }
    }
}