package com.senai.miniprojetoeducationm1s12.service;

import com.senai.miniprojetoeducationm1s12.entity.AlunoEntity;
import java.util.List;

public interface AlunoService {
    List<AlunoEntity> buscarTodos();
    AlunoEntity buscarPorId(Long id);
    AlunoEntity criar(AlunoEntity entity);
    AlunoEntity alterar(Long id, AlunoEntity entity);
    void excluir(Long id);
}
