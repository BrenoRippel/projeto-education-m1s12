package com.senai.miniprojetoeducationm1s12.repository;

import com.senai.miniprojetoeducationm1s12.entity.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {
}