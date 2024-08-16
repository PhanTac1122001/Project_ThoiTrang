package com.n3.project_thoitrang.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "banner")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banner_id")
    private Long id;
    @Column(name = "title", nullable = false, length = 100)
    private String title;
    @Column(name = "description", nullable = false, length = 100)
    private String description;
    @Column(name = "image", nullable = false, length = 100)
    private String image;
    @Column(name = "status", nullable = false)
    private Boolean status;
}
