package com.n3.project_thoitrang.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product_images")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product_Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "product_detail_id", nullable = false)
    private Product_Detail productDetail;

    @Column(name = "image_name", nullable = false, length = 100)
    private String imageName;

    @Column(name = "status", nullable = false)
    private boolean status;

    // Getters and Setters
}