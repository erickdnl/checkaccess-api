package com.zumr.checkaccess_api.repository;

import com.zumr.checkaccess_api.domain.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository  extends MongoRepository<Review, String> {

    List<Review> findByPlaceId(String placeId);
}
