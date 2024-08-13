package com.n3.project_thoitrang.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "wish_list")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Wish_List {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wish_list_id")
    private Long wishListId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;


}