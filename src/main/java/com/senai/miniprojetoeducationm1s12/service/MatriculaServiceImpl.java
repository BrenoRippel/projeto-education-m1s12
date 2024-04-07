package com.senai.miniprojetoeducationm1s12.service;

import com.senai.miniprojetoeducationm1s12.entity.AlunoEntity;
import com.senai.miniprojetoeducationm1s12.entity.DisciplinaEntity;
import com.senai.miniprojetoeducationm1s12.entity.MatriculaEntity;
import com.senai.miniprojetoeducationm1s12.exceptions.error.MatriculaByIdNotFoundException;
import com.senai.miniprojetoeducationm1s12.repository.MatriculaRepository;
import com.senai.miniprojetoeducationm1s12.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MatriculaServiceImpl implements MatriculaService{
    private final MatriculaRepository repository;
    private final AlunoServiceImpl repositoryAluno;
    private final DisciplinaServiceImpl repositoryDisciplina;

    public MatriculaServiceImpl(MatriculaRepository repository, AlunoServiceImpl repositoryAluno, DisciplinaServiceImpl repositoryDisciplina) {
        this.repository = repository;
        this.repositoryAluno = repositoryAluno;
        this.repositoryDisciplina = repositoryDisciplina;
    }


    @Override
    public List<MatriculaEntity> buscarTodos() {
        log.info("Buscando todos as matrícula");

        List<MatriculaEntity> entities = repository.findAll();
        log.info("Buscando todos os matrícula -> {} Encontrados", entities.size());

        log.debug("Buscando todos os matrícula -> Registros encontrados:\n{}\n", JsonUtil.objectToJson(entities));
        return entities;
    }

    @Override
    public MatriculaEntity buscarPorId(Long id) {
        log.info("Buscando matrícula por id ({})", id);
        Optional<MatriculaEntity> entity = repository.findById(id);

        if (entity.isEmpty()) {
            log.error("Buscando matrícula por id ({}) -> NÃO Encontrado", id);
            throw new MatriculaByIdNotFoundException(id);
        }

        log.info("Buscando matrícula por id ({}) -> Encontrado", id);
        log.debug("Buscando matrícula por id ({}) -> Registro encontrado:\n{}\n", id, JsonUtil.objectToJson(entity.get()));
        return entity.get();
    }


    @Override
    public MatriculaEntity criar(Long idAluno, Long idDisciplina) {
        MatriculaEntity entity = null;
        AlunoEntity aluno = repositoryAluno.buscarPorId(idAluno);
        DisciplinaEntity disciplina = repositoryDisciplina.findById(idDisciplina);

        entity.setAluno(aluno);
        entity.setDisciplina(disciplina);

        log.info("Criando matrícula -> Salvar: \n{}\n", JsonUtil.objectToJson(entity));
        MatriculaEntity matricula = repository.save(entity);

        log.info("Criando matrícula -> Salvo com sucesso");
        log.debug("Criando matrícula -> Registro Salvo: \n{}\n", JsonUtil.objectToJson(entity));
        return matricula;
    }

    @Override
    public List<MatriculaEntity> buscarAlunoPorId(Long idAluno) {
        log.info("Buscando matrículas por id de aluno ({})", idAluno);
        List<MatriculaEntity> entities = repository.findAllByAlunoId(idAluno);

        if (entities.isEmpty()) {
            log.error("Buscando matrículas por id de aluno ({}) -> NÃO Encontrado", idAluno);
            throw new MatriculaByIdNotFoundException(idAluno);
        }

        log.info("Buscando matrículas por id de aluno ({}) -> Encontrado", idAluno);
        log.debug("Buscando matrículas por id de aluno ({}) -> Registro encontrado:\n{}\n", idAluno, JsonUtil.objectToJson(entities));

        return entities;
    }

    @Override
    public List<MatriculaEntity> buscarDisciplinaPorId(Long idDisciplina) {
        log.info("Buscando matrículas por id de disciplina ({})", idDisciplina);
        List<MatriculaEntity> entities = repository.findAllByDisciplinaId(idDisciplina);

        if (entities.isEmpty()) {
            log.error("Buscando matrículas por id de disciplina ({}) -> NÃO Encontrado", idDisciplina);
            throw new MatriculaByIdNotFoundException(idDisciplina);
        }

        log.info("Buscando matrículas por id de disciplina ({}) -> Encontrado", idDisciplina);
        log.debug("Buscando matrículas por id de disciplina ({}) -> Registro encontrado:\n{}\n", idDisciplina, JsonUtil.objectToJson(entities));

        return entities;
    }

    @Override
    public void excluir(Long id) {
        MatriculaEntity entity = buscarPorId(id);
        log.info("Excluindo matrícula com id ({}) -> Excluindo", id);

        if (entity.getMediaFinal() > 0) {
            log.error("Excluindo matrícula com id ({}) -> Falha, Matrícula já possuí notas lançadas, é possível excluir", id);
            throw new MatriculaByIdNotFoundException(id);
        }

        repository.delete(entity);
        log.info("Excluindo matrícula com id ({}) -> Excluído com sucesso", id);
    }
}
