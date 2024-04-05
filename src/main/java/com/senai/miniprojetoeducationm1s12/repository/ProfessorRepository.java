package com.senai.miniprojetoeducationm1s12.repository;

import com.senai.miniprojetoeducationm1s12.entity.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
}
