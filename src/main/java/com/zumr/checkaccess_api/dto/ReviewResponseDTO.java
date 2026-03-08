package com.zumr.checkaccess_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class ReviewResponseDTO {

    private Integer rating;
    private String comment;
    private Instant createdAt;
}
