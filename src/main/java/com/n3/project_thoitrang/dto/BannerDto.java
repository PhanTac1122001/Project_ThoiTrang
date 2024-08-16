package com.n3.project_thoitrang.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor


@Getter
@Setter
@Builder
public class BannerDto {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String title;
    private String description;
    private MultipartFile image;
    private Boolean status = true;
}
