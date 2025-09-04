package com.example.ratingreviewservice.service;

import com.example.ratingreviewservice.model.Customer;
import com.example.ratingreviewservice.model.Product;
import com.example.ratingreviewservice.model.Review;
import com.example.ratingreviewservice.repository.ReviewRepository;
import com.example.ratingreviewservice.repository.ProductRepository;
import com.example.ratingreviewservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         ProductRepository productRepository,
                         CustomerRepository customerRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    public List<Review> getByProduct(Long productId) {
        return reviewRepository.findByProduct_Id(productId);
    }

    public List<Review> getByCustomer(Long customerId) {
        return reviewRepository.findByCustomer_Id(customerId);
    }

    @Transactional
    public Review addReview(Review review, Long productId, Long customerId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerId));
        review.setProduct(product);
        review.setCustomer(customer);
        if (review.getCreatedAt() == null) {
            review.setCreatedAt(java.time.Instant.now());
        }
        return reviewRepository.save(review);
    }
}
