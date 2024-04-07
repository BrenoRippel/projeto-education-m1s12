package com.senai.miniprojetoeducationm1s12.service;

import com.senai.miniprojetoeducationm1s12.entity.AlunoEntity;
import com.senai.miniprojetoeducationm1s12.exceptions.error.NotFoundException;
import com.senai.miniprojetoeducationm1s12.repository.AlunoRepository;
import com.senai.miniprojetoeducationm1s12.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AlunoServiceImpl implements AlunoService {
    private final AlunoRepository alunoRepository;

    public AlunoServiceImpl(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Override
    public List<AlunoEntity> buscarTodos() {
        log.info("Buscando todos os alunos");
        List<AlunoEntity> alunos = alunoRepository.findAll();
        log.info("Buscando todos os alunos -> {} Total:", alunos.size());

        log.debug("Buscando todos os alunos -> Alunos encontrados:\n{}\n", JsonUtil.objectToJson(alunos));
        return alunos;
    }

    @Override
    public AlunoEntity buscarPorId(Long id) {
        log.info("Buscando todos os alunos por id");

        Optional<AlunoEntity> aluno = alunoRepository.findById(id);

        if (aluno.isEmpty()) {
            log.error("Buscando aluno por id ({}) -> Não foi encontrado!", id);
            throw new NotFoundException("Aluno ID:" + id + " não encontrado!");
        }

        log.info("Buscando aluno por id ({}) -> Encontrado", id);
        log.debug("Buscando aluno por id ({}) -> Aluno encontrado:\n{}\n", id, JsonUtil.objectToJson(aluno.get()));
        return aluno.get();
    }

    @Override
    public AlunoEntity criar(AlunoEntity entity) {
        log.info("Criando aluno -> Salvar: \n{}\n", JsonUtil.objectToJson(entity));
        AlunoEntity aluno = alunoRepository.save(entity);

        log.info("Criando aluno -> Salvo com sucesso");
        log.debug("Criando aluno -> Aluno cadastrado: \n{}\n", JsonUtil.objectToJson(entity));
        return aluno;
    }

    @Override
    public AlunoEntity alterar(Long id, AlunoEntity aluno) {
        log.info("Buscando aluno de ID: ", id);
        buscarPorId(id);
        aluno.setId(id);

        log.info("Atualizando aluno com id ({}) -> Salvar: \n{}\n", id, JsonUtil.objectToJson(aluno));
        aluno = alunoRepository.save(aluno);

        log.info("Atualizando aluno -> Salvo com sucesso");
        log.debug("Atualizando aluno -> Registro Salvo: \n{}\n", JsonUtil.objectToJson(aluno));
        return aluno;
    }

    @Override
    public void excluir(Long id) {
        AlunoEntity aluno = buscarPorId(id);
        log.info("Excluindo aluno com id ({}) -> Excluindo", id);

        alunoRepository.delete(aluno);
        log.info("Excluindo aluno com id ({}) -> Excluído com sucesso", id);
    }
}
