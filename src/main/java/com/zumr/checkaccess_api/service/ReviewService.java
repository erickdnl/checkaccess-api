package com.zumr.checkaccess_api.service;

import com.zumr.checkaccess_api.domain.Place;
import com.zumr.checkaccess_api.domain.Review;
import com.zumr.checkaccess_api.dto.*;
import com.zumr.checkaccess_api.repository.PlaceRepository;
import com.zumr.checkaccess_api.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
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
                        review.getRating(),
                        review.getComment(),
                        review.getCreatedAt()
                ))
                .toList();

        String message = reviews.isEmpty() ? "Seja o primeiro a avaliar" : null;

        return new PlaceReviewsResponseDTO(placeId, reviews, message);
    }

    public void createReview(String placeId, ReviewRequestDTO request) {

        if (!placeRepository.existsById(placeId)){
            Place place = new Place();
            place.setPlaceId(placeId);

            placeRepository.save(place);
        }

        Review review = new Review();

        review.setPlaceId(placeId);
        review.setUserId(request.getUserId());
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setCreatedAt(Instant.now());

        reviewRepository.save(review);
    }
}
