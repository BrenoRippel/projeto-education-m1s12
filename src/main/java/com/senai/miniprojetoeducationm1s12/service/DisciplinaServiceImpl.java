package com.senai.miniprojetoeducationm1s12.service;

import com.senai.miniprojetoeducationm1s12.entity.DisciplinaEntity;
import com.senai.miniprojetoeducationm1s12.repository.DisciplinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {

    private final DisciplinaRepository repository;

    public DisciplinaServiceImpl(DisciplinaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<DisciplinaEntity> get(Long id) {
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
    public DisciplinaEntity alter(Long id, DisciplinaEntity entity) {
        repository.deleteById(id);
        entity.setId(id);
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
