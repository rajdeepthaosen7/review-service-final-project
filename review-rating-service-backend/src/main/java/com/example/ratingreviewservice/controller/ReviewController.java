package com.example.ratingreviewservice.controller;

import com.example.ratingreviewservice.model.Review;
import com.example.ratingreviewservice.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/product/{productId}")
    public List<Review> getByProduct(@PathVariable Long productId) {
        return reviewService.getByProduct(productId);
    }

    @GetMapping("/customer/{customerId}")
    public List<Review> getByCustomer(@PathVariable Long customerId) {
        return reviewService.getByCustomer(customerId);
    }

    @PostMapping
    public Review addReview(@RequestBody Review review,
                            @RequestParam Long productId,
                            @RequestParam Long customerId) {
        return reviewService.addReview(review, productId, customerId);
    }
}
