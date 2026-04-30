package com.zumr.checkaccess_api.review.service;

import com.zumr.checkaccess_api.domain.*;
import com.zumr.checkaccess_api.repository.*;
import com.zumr.checkaccess_api.review.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;

    public PlaceReviewsResponseDTO getReviewsByPlace(String placeId) {

        log.info("Consultando reviews do placeId={}", placeId);

        List<ReviewResponseDTO> reviews = reviewRepository.findByPlaceId(placeId)
                .stream()
                .map(this::toResponse)
                .toList();

        String message = reviews.isEmpty() ? "Seja o primeiro a avaliar" : null;

        log.info("Consulta finalizada placeId={} totalReviews={}",
                placeId, reviews.size());

        return new PlaceReviewsResponseDTO(placeId, reviews, message);
    }

    public ReviewResponseDTO createReview(String placeId, ReviewRequestDTO request) {

        log.info("Iniciando criação de review placeId={}", placeId);

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

        log.info("Review criada com sucesso review={} placeId={} userId={}",
                saved.getId(), placeId, userId);

        return toResponse(saved);
    }

    private ReviewResponseDTO toResponse(Review review) {

        String userName = userRepository.findById(review.getUserId())
                .map(User::getUsername)
                .orElse("Usuário");

        return new ReviewResponseDTO(
                review.getId(),
                review.getPlaceId(),
                review.getUserId(),
                userName,
                review.getRating(),
                review.getComment(),
                review.getCreatedAt()
        );
    }
}
