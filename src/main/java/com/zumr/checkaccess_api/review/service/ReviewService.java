package com.zumr.checkaccess_api.review.service;

import com.zumr.checkaccess_api.domain.Place;
import com.zumr.checkaccess_api.domain.Review;
import com.zumr.checkaccess_api.repository.PlaceRepository;
import com.zumr.checkaccess_api.repository.ReviewRepository;
import com.zumr.checkaccess_api.review.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PlaceRepository placeRepository;

    public PlaceReviewsResponseDTO getReviewsByPlace(String placeId) {

        List<ReviewResponseDTO> reviews = reviewRepository.findByPlaceId(placeId)
                .stream()
                .map(review -> new ReviewResponseDTO(
                        review.getId(),
                        review.getPlaceId(),
                        review.getUserId(),
                        review.getRating(),
                        review.getComment(),
                        review.getCreatedAt()
                ))
                .toList();

        String message = reviews.isEmpty() ? "Seja o primeiro a avaliar" : null;

        return new PlaceReviewsResponseDTO(placeId, reviews, message);
    }

    public ReviewResponseDTO createReview(String placeId, ReviewRequestDTO request) {

        if (!placeRepository.existsById(placeId)){
            Place place = new Place();
            place.setPlaceId(placeId);
            placeRepository.save(place);
        }

        String userId = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        Review review = new Review();
        review.setPlaceId(placeId);
        review.setUserId(userId);
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setCreatedAt(Instant.now());

        Review saved = reviewRepository.save(review);

        return new ReviewResponseDTO(
                saved.getId(),
                saved.getPlaceId(),
                saved.getUserId(),
                saved.getRating(),
                saved.getComment(),
                saved.getCreatedAt()
        );
    }
}
