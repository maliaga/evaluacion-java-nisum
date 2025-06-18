package dev.mario.evaluacionjavanisum.infrastructure.adapters.repositories.entity;

import dev.mario.evaluacionjavanisum.domain.model.PhoneDom;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PhoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;
    private int cityCode;
    private int countryCode;

    public static List<PhoneEntity> fromList(List<PhoneDom> phoneDoms) {
        return phoneDoms.stream()
                .map(phoneDom -> PhoneEntity.builder()
                        .id(phoneDom.getId())
                        .number(phoneDom.getNumber())
                        .cityCode(phoneDom.getCityCode())
                        .countryCode(phoneDom.getCountryCode())
                        .build())
                .toList();
    }
}