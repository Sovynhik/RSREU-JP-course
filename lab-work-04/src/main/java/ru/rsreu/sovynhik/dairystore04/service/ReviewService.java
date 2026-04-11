package ru.rsreu.sovynhik.dairystore04.service;

import ru.rsreu.sovynhik.dairystore04.document.Customer;
import ru.rsreu.sovynhik.dairystore04.document.Product;
import ru.rsreu.sovynhik.dairystore04.document.Review;
import ru.rsreu.sovynhik.dairystore04.dto.FeedbackForm;
import ru.rsreu.sovynhik.dairystore04.repository.CustomerRepository;
import ru.rsreu.sovynhik.dairystore04.repository.ProductRepository;
import ru.rsreu.sovynhik.dairystore04.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class ReviewService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public ReviewService(CustomerRepository customerRepository,
                         ProductRepository productRepository,
                         ReviewRepository reviewRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public void saveReview(FeedbackForm feedbackForm, String productName) {
        Customer customer = customerRepository.findByEmail(feedbackForm.getEmail())
                .orElseGet(() -> {
                    Customer newCustomer = new Customer();
                    newCustomer.setName(feedbackForm.getName());
                    newCustomer.setEmail(feedbackForm.getEmail());
                    newCustomer.setPhone("не указан");
                    return customerRepository.save(newCustomer);
                });

        Product product = productRepository.findByName(productName)
                .orElseThrow(() -> new RuntimeException("Товар не найден: " + productName));

        Review review = new Review();
        review.setCustomer(customer);
        review.setProduct(product);
        review.setRating(feedbackForm.getRating());
        review.setComment(feedbackForm.getComment());
        review.setCreatedAt(LocalDateTime.now());

        reviewRepository.save(review);
    }
}