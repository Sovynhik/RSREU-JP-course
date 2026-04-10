package ru.rsreu.sovynhik.dairystore03.service;

import ru.rsreu.sovynhik.dairystore03.dto.OrderForm;
import ru.rsreu.sovynhik.dairystore03.entity.Customer;
import ru.rsreu.sovynhik.dairystore03.entity.Order;
import ru.rsreu.sovynhik.dairystore03.entity.Product;
import ru.rsreu.sovynhik.dairystore03.repository.CustomerRepository;
import ru.rsreu.sovynhik.dairystore03.repository.OrderRepository;
import ru.rsreu.sovynhik.dairystore03.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class OrderService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderService(CustomerRepository customerRepository,
                        ProductRepository productRepository,
                        OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void placeOrder(OrderForm orderForm) {
        Customer customer = customerRepository.findByEmail(orderForm.getEmail())
                .orElseGet(() -> {
                    Customer newCustomer = new Customer();
                    newCustomer.setName(orderForm.getCustomerName());
                    newCustomer.setPhone(orderForm.getPhone());
                    newCustomer.setEmail(orderForm.getEmail());
                    return customerRepository.save(newCustomer);
                });

        Product product = productRepository.findByName(orderForm.getProduct())
                .orElseThrow(() -> new RuntimeException("Товар не найден: " + orderForm.getProduct()));

        Order order = new Order();
        order.setCustomer(customer);
        order.setProduct(product);
        order.setQuantity(Integer.parseInt(orderForm.getQuantity()));
        order.setOrderDate(LocalDateTime.now());

        orderRepository.save(order);
    }
}
