package com.senai.miniprojetoeducationm1s12.service;

import com.senai.miniprojetoeducationm1s12.entity.DisciplinaEntity;
import com.senai.miniprojetoeducationm1s12.repository.DisciplinaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DisciplinaServiceImpl implements DisciplinaService {

    private final DisciplinaRepository repository;

    public DisciplinaServiceImpl(DisciplinaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<DisciplinaEntity> get(Long id) {
        log.info("SERVICE /disciplina -> Buscando por ID: {}", id);
        return repository.findById(id);
    }

    @Override
    public List<DisciplinaEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public DisciplinaEntity create(DisciplinaEntity entity) {
        return repository.save(entity);
    }

    @Override
    public DisciplinaEntity alter(Long id, DisciplinaEntity entityData) {
        DisciplinaEntity foundEntity = findById(id);
        return setEntity(foundEntity, entityData);
    }

    private DisciplinaEntity setEntity(DisciplinaEntity entity, DisciplinaEntity entityData) {
        entity.setNome(entityData.getNome());
//        entity.setProfessor(entityData.getProfessor());
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        DisciplinaEntity entity = findById(id);
        repository.delete(entity);
    }

    public DisciplinaEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Disciplina não encontrada com id: "+ id
                        ));
    }
}
