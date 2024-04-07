package com.senai.miniprojetoeducationm1s12.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MatriculaFiltro implements Serializable {
    private Long idAluno;
    private Long idDisciplina;
}
