package com.senai.miniprojetoeducationm1s12.service;

import com.senai.miniprojetoeducationm1s12.entity.MatriculaEntity;

import java.util.List;

public interface MatriculaService {
    List<MatriculaEntity> buscarTodos();
    MatriculaEntity buscarPorId(Long id);
    MatriculaEntity criar(Long idAluno, Long idDisciplina);
    List<MatriculaEntity> buscarAlunoPorId(Long idAluno);
    List<MatriculaEntity> buscarDisciplinaPorId(Long idDisciplina);
    void excluir(Long id);
}
