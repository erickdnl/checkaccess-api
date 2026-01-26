package com.zumr.checkacess_api.domain;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Place {

    @EqualsAndHashCode.Include
    private String placeId;

    private String name;
}
