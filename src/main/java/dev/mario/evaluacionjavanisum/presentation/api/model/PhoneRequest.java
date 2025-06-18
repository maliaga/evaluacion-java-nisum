package dev.mario.evaluacionjavanisum.presentation.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mario.evaluacionjavanisum.domain.model.PhoneDom;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PhoneRequest {
    private Integer number;
    @JsonProperty("citycode")
    private int cityCode;
    @JsonProperty("contrycode")
    private int contryCode;

    public static List<PhoneDom> toDomainList(List<PhoneRequest> phoneRequest) {
        return phoneRequest.stream()
                .map(phone -> PhoneDom.builder()
                        .number(phone.getNumber())
                        .cityCode(phone.getCityCode())
                        .countryCode(phone.getContryCode())
                        .build())
                .toList();
    }
}
