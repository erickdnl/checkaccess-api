package com.zumr.checkaccess_api.repository;

import com.zumr.checkaccess_api.domain.Place;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlaceRepository extends MongoRepository<Place, String> {
}
