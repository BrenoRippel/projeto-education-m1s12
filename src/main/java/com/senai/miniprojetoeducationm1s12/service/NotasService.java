package com.senai.miniprojetoeducationm1s12.service;

import com.senai.miniprojetoeducationm1s12.entity.NotasEntity;

import java.util.List;

public interface NotasService {
    List<NotasEntity> buscarTodasPorMatriculaId(Long matriculaId);
    NotasEntity buscarPorId(Long id);
    NotasEntity criar(NotasEntity entity);
    void excluir(Long id);
}
