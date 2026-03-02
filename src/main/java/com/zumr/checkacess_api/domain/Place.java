package com.zumr.checkacess_api.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Place {

    @EqualsAndHashCode.Include
    private String placeId;

    private String name;

    private List<Review> reviews = new ArrayList<>();
}
