package com.n3.project_thoitrang.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor


@Getter
@Setter
@Builder
public class CategoryDto {
   private Long id;
   @NotBlank(message = "tên không được để trống")
   private String name;
   private String description;
   private Boolean status = true;
}
