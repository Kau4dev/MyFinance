package com.Dev.MyFinance.models;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false,unique = true , length = 100)
    private String email;

   @Column(nullable = false, length = 250)
    private String senha;

}
