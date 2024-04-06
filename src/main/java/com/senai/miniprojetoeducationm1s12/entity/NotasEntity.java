package com.senai.miniprojetoeducationm1s12.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "notas")
@Data
public class NotasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "disciplina_matricula", nullable = false)
    private DisciplinaEntity disciplina;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private ProfessorEntity professor;

    @JoinColumn(nullable = false)
    private Float nota;

    @JoinColumn(nullable = false)
    private Float coeficiente;

}
