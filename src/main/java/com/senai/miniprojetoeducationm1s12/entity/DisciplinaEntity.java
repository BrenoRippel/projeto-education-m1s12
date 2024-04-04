package com.senai.miniprojetoeducationm1s12.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="disciplina")
public class DisciplinaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private ProfessorEntity professor;
}
