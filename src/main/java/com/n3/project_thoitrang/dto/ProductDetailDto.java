package com.n3.project_thoitrang.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor


@Getter
@Setter
@Builder
public class ProductDetailDto {

    private Long product;
    private String sku;
    private Double unitPrice;
    private int stockQuantity;
    private MultipartFile image;
    private Long color;
    private Long size;

}
