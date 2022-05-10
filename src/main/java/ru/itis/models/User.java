package ru.itis.models;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    private String nickname;

    //0,75 на кг веса
    private Integer weight;

    @OneToMany(mappedBy = "user")
    private List<Auth> auth;
}
