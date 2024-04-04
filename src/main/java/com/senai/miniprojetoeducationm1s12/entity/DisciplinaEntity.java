package com.senai.miniprojetoeducationm1s12.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "disciplina")
public class DisciplinaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private ProfessorEntity professor;
}
