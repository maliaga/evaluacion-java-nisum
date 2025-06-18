package dev.mario.evaluacionjavanisum.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDom {

    private Long id;
    private Integer number;
    private int cityCode;
    private int countryCode;
}
