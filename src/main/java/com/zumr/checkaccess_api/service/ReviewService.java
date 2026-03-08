package com.zumr.checkaccess_api.service;

import com.zumr.checkaccess_api.dto.PlaceReviewsResponseDTO;
import com.zumr.checkaccess_api.dto.ReviewResponseDTO;
import com.zumr.checkaccess_api.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

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
}
