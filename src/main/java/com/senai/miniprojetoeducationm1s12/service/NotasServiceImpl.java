package com.senai.miniprojetoeducationm1s12.service;

import com.senai.miniprojetoeducationm1s12.entity.DisciplinaEntity;
import com.senai.miniprojetoeducationm1s12.entity.MatriculaEntity;
import com.senai.miniprojetoeducationm1s12.entity.NotasEntity;
import com.senai.miniprojetoeducationm1s12.exceptions.ErrorHandler;
import com.senai.miniprojetoeducationm1s12.exceptions.error.NotFoundException;
import com.senai.miniprojetoeducationm1s12.repository.MatriculaRepository;
import com.senai.miniprojetoeducationm1s12.repository.NotasRepository;
import com.senai.miniprojetoeducationm1s12.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class NotasServiceImpl implements NotasService {
    private final NotasRepository notasRepository;
    private final MatriculaRepository matriculaRepository;

    public NotasServiceImpl(NotasRepository notasRepository, MatriculaRepository matriculaRepository) {
        this.notasRepository = notasRepository;
        this.matriculaRepository = matriculaRepository;
    }

    @Override
    public List<NotasEntity> getNotasByMatriculaId(Long matriculaId) {
        MatriculaEntity matricula = matriculaRepository.findById(matriculaId)
                .orElseThrow(() -> new ErrorHandler.ResourceNotFoundException("Matricula de id: " + matriculaId + " não encontrado"));

        return notasRepository.findAllByMatriculaId(matricula.getId());
    }

//    public List<NotasEntity> getNotasByDisciplinaId(Long disciplinaId) {
//        DisciplinaEntity disciplina = notasRepository.findById(disciplinaId)
//                .orElseThrow(() -> new ErrorHandler.ResourceNotFoundException("Nota de id: "+ disciplinaId +" não encontrado"));
//        return notasRepository.findAllByDisciplinaId(disciplina.getId());
//    }

    @Override
    public NotasEntity criar(NotasEntity notas) {
        log.info("Salvando nota -> Salvar: \n{}\n", JsonUtil.objectToJson(notas));
        NotasEntity entity = notasRepository.save(notas);

        log.info("Salvando nota -> Salvo com sucesso");
        log.debug("Salvando nota -> Registro Salvo: \n{}\n", JsonUtil.objectToJson(entity));
        return entity;
    }

    @Override
    public void excluir(Long id) {

    }
}