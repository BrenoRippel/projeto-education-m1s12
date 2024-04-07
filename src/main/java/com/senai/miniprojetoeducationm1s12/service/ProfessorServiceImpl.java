package com.senai.miniprojetoeducationm1s12.service;

import com.senai.miniprojetoeducationm1s12.entity.ProfessorEntity;
import com.senai.miniprojetoeducationm1s12.exceptions.error.ProfessorByIdNotFoundException;
import com.senai.miniprojetoeducationm1s12.repository.ProfessorRepository;
import com.senai.miniprojetoeducationm1s12.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProfessorServiceImpl implements ProfessorService{
    private final ProfessorRepository repository;

    public ProfessorServiceImpl(ProfessorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProfessorEntity> buscarTodos() {
        log.info("Buscando todos os professores");

        List<ProfessorEntity> entities = repository.findAll();
        log.info("Buscando todos os professores -> {} Encontrados", entities.size());

        log.debug("Buscando todos os professores -> Registros encontrados:\n{}\n", JsonUtil.objectToJson(entities));
        return entities;
    }

    @Override
    public ProfessorEntity buscarPorId(Long id) {
        log.info("Buscando professor por id ({})", id);
        Optional<ProfessorEntity> entity = repository.findById(id);

        if (entity.isEmpty()) {
            log.error("Buscando professor por id ({}) -> NÃO Encontrado", id);
            throw new ProfessorByIdNotFoundException(id);
        }

        log.info("Buscando professor por id ({}) -> Encontrado", id);
        log.debug("Buscando professor por id ({}) -> Registro encontrado:\n{}\n", id, JsonUtil.objectToJson(entity.get()));
        return entity.get();
    }

    @Override
    public ProfessorEntity criar(ProfessorEntity entity) {
        entity.setId(null);

        log.info("Criando professor -> Salvar: \n{}\n", JsonUtil.objectToJson(entity));
        ProfessorEntity professor = repository.save(entity);

        log.info("Criando professor -> Salvo com sucesso");
        log.debug("Criando professor -> Registro Salvo: \n{}\n", JsonUtil.objectToJson(entity));
        return professor;
    }

    @Override
    public ProfessorEntity alterar(Long id, ProfessorEntity entity) {
        ProfessorEntity professor = buscarPorId(id);
        professor.setNome(entity.getNome());

        log.info("Alterando professor com id ({}) -> Salvar: \n{}\n", id, JsonUtil.objectToJson(entity));
        professor = repository.save(entity);

        log.info("Alterando professor -> Salvo com sucesso");
        log.debug("Alterando professor -> Registro Salvo: \n{}\n", JsonUtil.objectToJson(entity));
        return professor;
    }

    @Override
    public void excluir(Long id) {
        ProfessorEntity entity = buscarPorId(id);
        log.info("Excluindo professor com id ({}) -> Excluindo", id);

        repository.delete(entity);
        log.info("Excluindo professor com id ({}) -> Excluído com sucesso", id);
    }
}
