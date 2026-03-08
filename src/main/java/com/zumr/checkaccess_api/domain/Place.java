package com.zumr.checkaccess_api.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "places")
public class Place {

    @Id
    @EqualsAndHashCode.Include
    private String placeId;
    private String name;
}
