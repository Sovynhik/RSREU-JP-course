package ru.rsreu.sovynhik.dairystore04.repository;

import ru.rsreu.sovynhik.dairystore04.document.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {
}