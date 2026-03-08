package com.zumr.checkaccess_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaceReviewsResponseDTO {

    private String placeId;
    private List<ReviewResponseDTO> reviews;
    private String message;
}
