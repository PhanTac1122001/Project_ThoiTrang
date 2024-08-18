package com.n3.project_thoitrang.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "shoping_cart")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Shoping_Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_cart_id")
    private Long shoppingCartId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product_Detail productDetail;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "order_quantity", nullable = false)
    private int orderQuantity;

}