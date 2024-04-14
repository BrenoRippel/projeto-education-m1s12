package com.senai.miniprojetoeducationm1s12.service;

import com.senai.miniprojetoeducationm1s12.dto.MediaGeralFiltro;
import com.senai.miniprojetoeducationm1s12.entity.AlunoEntity;
import com.senai.miniprojetoeducationm1s12.entity.DisciplinaEntity;
import com.senai.miniprojetoeducationm1s12.entity.MatriculaEntity;
import com.senai.miniprojetoeducationm1s12.exceptions.error.MatriculaByIdNotFoundException;
import com.senai.miniprojetoeducationm1s12.repository.MatriculaRepository;
import com.senai.miniprojetoeducationm1s12.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MatriculaServiceImpl implements MatriculaService{
    private final MatriculaRepository repository;
    private final AlunoServiceImpl repositoryAluno;
    private final DisciplinaServiceImpl repositoryDisciplina;
    private final NotasServiceImpl serviceNotas;

    public MatriculaServiceImpl(MatriculaRepository repository, AlunoServiceImpl repositoryAluno, DisciplinaServiceImpl repositoryDisciplina, NotasServiceImpl serviceNotas) {
        this.repository = repository;
        this.repositoryAluno = repositoryAluno;
        this.repositoryDisciplina = repositoryDisciplina;
        this.serviceNotas = serviceNotas;
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
        MatriculaEntity entity = new MatriculaEntity();
        AlunoEntity aluno = repositoryAluno.buscarPorId(idAluno);
        DisciplinaEntity disciplina = repositoryDisciplina.findById(idDisciplina);
        LocalDate dataAtual = LocalDate.now();

        entity.setAluno(aluno);
        entity.setDisciplina(disciplina);
        entity.setDataMatricula(dataAtual);
        entity.setMediaFinal(0.0F);

        log.info("Criando matrícula -> Salvar: \n{}\n", JsonUtil.objectToJson(entity));
        MatriculaEntity matricula = repository.save(entity);

        log.info("Criando matrícula -> Salvo com sucesso");
        log.debug("Criando matrícula -> Registro Salvo: \n{}\n", JsonUtil.objectToJson(entity));
        return matricula;
    }

    @Override
    public List<MatriculaEntity> buscarPorAlunoId(Long idAluno) {
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

    public List<MediaGeralFiltro> getMediasFinaisByAlunoId(Long id) {
        List<MediaGeralFiltro> dtoList = new ArrayList<>();
        List<MatriculaEntity> matriculas = buscarPorAlunoId(id);

        for (MatriculaEntity matricula : matriculas) {
            MediaGeralFiltro dto = new MediaGeralFiltro();
            dto.setDisciplina(matricula.getDisciplina().getNome());
            dto.setMediaFinal(matricula.getMediaFinal());
            dtoList.add(dto);
        }
        MediaGeralFiltro dto = new MediaGeralFiltro();
        dto.setDisciplina("Média Geral");

        dto.setMediaFinal(calcularMediaGeral(matriculas));
        dtoList.add(dto);
        return dtoList;
    }

    public Float calcularMediaGeral(List<MatriculaEntity> matriculas) {
        float soma = 0;

        for (MatriculaEntity matricula : matriculas) {
            soma += matricula.getMediaFinal();
        }

        return matriculas.isEmpty() ? 0 : (soma / matriculas.size());
    }

//    public List<MediaGeralDatas> calcularMediaFinalPorAlunoId(Long id) {
//        List<MediaGeralDatas> dtoList = new ArrayList<>();
//        List<MatriculaEntity> matriculas = buscarPorAlunoId(id);
//    }
//
//    public List<MediaGeralDatas> calcularMatriculaPorAlunoId(Long id) {
//        List<MediaGeralDatas> dtoList = new ArrayList<>();
//        List<MatriculaEntity> matriculas = buscarPorAlunoId(id);
//        for (MatriculaEntity matricula : matriculas) {
//            MediaGeralDatas dto = new MediaGeralDatas();
//            DisciplinaEntity disciplina = matricula.getDisciplina();
//            dto.setDisciplina(disciplina.getNome());
//            List<NotasEntity> notasMatricula = serviceNotas.getNotasByMatriculaId(matricula.getId());
//            for (NotasEntity nota : notasMatricula)
//// TODO: Terminar código e ajuste de DTO
//            dtoList.add(dto);
//        }
//        return dtoList;
//    }
}
