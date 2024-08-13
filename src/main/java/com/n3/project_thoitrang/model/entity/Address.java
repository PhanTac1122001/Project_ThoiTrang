package com.n3.project_thoitrang.model.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "full_address", nullable = false, length = 255)
    private String fullAddress;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @Column(name = "receive_name", nullable = false, length = 50)
    private String receiveName;

}