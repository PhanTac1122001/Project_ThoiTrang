package com.n3.project_thoitrang.model.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "sizes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "size_id")
    private Long sizeId;

    @Column(name = "size_name", nullable = false, length = 10)
    private String sizeName;

    @Column(name = "status", nullable = false)
    private boolean status;

    // Getters and Setters
}