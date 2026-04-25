package com.zumr.checkaccess_api.review.controller;

import com.zumr.checkaccess_api.review.dto.*;
import com.zumr.checkaccess_api.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/places/{placeId}/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<PlaceReviewsResponseDTO> getReviewsByPlace(@PathVariable String placeId) {

        PlaceReviewsResponseDTO response = reviewService.getReviewsByPlace(placeId);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ReviewResponseDTO> createReview(
            @PathVariable String placeId,
            @Valid @RequestBody ReviewRequestDTO request){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reviewService.createReview(placeId, request));
    }
}
