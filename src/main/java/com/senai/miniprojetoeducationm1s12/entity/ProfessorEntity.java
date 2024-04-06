package com.senai.miniprojetoeducationm1s12.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "professor")
@Data
public class ProfessorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
}
