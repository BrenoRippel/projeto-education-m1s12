package com.senai.miniprojetoeducationm1s12.service;

import com.senai.miniprojetoeducationm1s12.entity.DisciplinaEntity;

import java.util.List;
import java.util.Optional;

public interface DisciplinaService {

    Optional<DisciplinaEntity> get(Long id);

    List<DisciplinaEntity> getAll();
    DisciplinaEntity create(DisciplinaEntity entity);

    DisciplinaEntity alter(Long id, DisciplinaEntity entity);

    void delete(Long id);
}
