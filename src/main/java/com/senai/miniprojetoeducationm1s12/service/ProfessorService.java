package com.senai.miniprojetoeducationm1s12.service;



import com.senai.miniprojetoeducationm1s12.entity.ProfessorEntity;

import java.util.List;

public interface ProfessorService {
    List<ProfessorEntity> buscarTodos();
    ProfessorEntity buscarPorId(Long id);
    ProfessorEntity criar(ProfessorEntity entity);
    ProfessorEntity alterar(Long id, ProfessorEntity entity);
    void excluir(Long id);
}
