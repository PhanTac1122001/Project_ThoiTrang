package com.n3.project_thoitrang.model.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "colors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id")
    private Long colorId;

    @Column(name = "color_name", nullable = false, length = 100)
    private String colorName;

    @Column(name = "status", nullable = false)
    private boolean status;

    // Getters and Setters
}
