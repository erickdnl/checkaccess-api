package com.zumr.checkaccess_api.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class ReviewResponseDTO {

    private String id;
    private String placeId;
    private String userId;
    private String userName;
    private Integer rating;
    private String comment;
    private Instant createdAt;
}
