package com.n3.project_thoitrang.dto;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor


@Getter
@Setter
@Builder
public class SizeDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String sizeId;
    private String sizeName;
    private Boolean status = true;
}
