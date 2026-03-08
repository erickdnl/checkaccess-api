package com.zumr.checkaccess_api.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reviews")
public class Review {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String placeId;
    private String userId;
    private Integer rating;
    private String comment;
    private Instant createdAt;
}
