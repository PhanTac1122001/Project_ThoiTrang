
package com.n3.project_thoitrang.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username", nullable = false, length = 100, unique = true)
    private String username;

    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "fullname", length = 100)
    private String fullname;

    @Column(name = "status")
    private boolean status;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "avatar", length = 255)
    private String avatar;

    @Column(name = "phone", length = 15, unique = true)
    private String phone;

    @Column(name = "address", length = 255)
    private String address;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt = new Date();

    @Temporal(TemporalType.DATE)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<Role> role;
}