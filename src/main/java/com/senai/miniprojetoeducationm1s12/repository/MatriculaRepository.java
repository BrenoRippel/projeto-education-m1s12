package com.senai.miniprojetoeducationm1s12.repository;

import com.senai.miniprojetoeducationm1s12.entity.MatriculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaRepository extends JpaRepository<MatriculaEntity, Long> {
    List<MatriculaEntity> findAllByIdAluno(Long idAluno);
    List<MatriculaEntity> findAllByDisciplinaId(Long idDisciplina);
}
