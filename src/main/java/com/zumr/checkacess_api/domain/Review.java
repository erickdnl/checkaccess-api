package com.zumr.checkacess_api.domain;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @EqualsAndHashCode.Include
    private String id;

    private Integer rating;
    private String comment;
    private User user;
    private Place place;
}
