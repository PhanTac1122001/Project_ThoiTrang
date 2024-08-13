package com.n3.project_thoitrang.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "serial_number", nullable = false, length = 100, unique = true)
    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    public enum OrderStatus {
        WAITING, CONFIRM, DELIVERY, SUCCESS, CANCEL, DENIED
    }

    @Column(name = "note", length = 100)
    private String note;

    @Column(name = "receive_name", length = 100)
    private String receiveName;

    @Column(name = "receive_address", length = 255)
    private String receiveAddress;

    @Column(name = "receive_phone", length = 15)
    private String receivePhone;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at", nullable = false)
    private Date createdAt = new Date();

    @Temporal(TemporalType.DATE)
    @Column(name = "received_at")
    private Date receivedAt;

    // Getters and Setters
}