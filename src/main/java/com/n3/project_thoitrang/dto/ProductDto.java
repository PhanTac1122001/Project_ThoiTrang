package com.n3.project_thoitrang.dto;

import com.n3.project_thoitrang.model.entity.Category;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor


@Getter
@Setter
@Builder
public class ProductDto {
    private Long productId;
    @NotBlank(message = "tên không được để trống")
    private String productName;
    private String description;
    private Category category;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date createdAt = new Date();
    private Date updatedAt;
}
