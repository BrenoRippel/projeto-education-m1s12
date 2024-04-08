package com.senai.miniprojetoeducationm1s12.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "disciplina_matricula")
@Data
public class MatriculaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private AlunoEntity aluno;

    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false)
    private DisciplinaEntity disciplina;

    @Column(name = "data_matricula", nullable = false)
    private Date dataMatricula;

    @Column(name = "media_final", nullable = false)
    private Float mediaFinal;

}
