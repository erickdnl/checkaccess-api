package com.zumr.checkacess_api.repository;

import com.zumr.checkacess_api.domain.Place;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlaceRepository extends MongoRepository<Place, String> {
}
