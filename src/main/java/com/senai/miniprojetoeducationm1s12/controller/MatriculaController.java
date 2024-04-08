package com.senai.miniprojetoeducationm1s12.controller;

import com.senai.miniprojetoeducationm1s12.dto.MatriculaFiltro;
import com.senai.miniprojetoeducationm1s12.entity.MatriculaEntity;
import com.senai.miniprojetoeducationm1s12.service.MatriculaServiceImpl;
import com.senai.miniprojetoeducationm1s12.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("matriculas")
public class MatriculaController {
    private final MatriculaServiceImpl service;

    public MatriculaController(MatriculaServiceImpl service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<MatriculaEntity> getId(@PathVariable Long id) {
        log.info("GET /matrículas/{} -> Início", id);

        MatriculaEntity entity = service.buscarPorId(id);
        log.info("GET /matrículas/{} -> Encontrado", id);

        log.info("GET /matrículas/{} -> 200 OK", id);
        log.debug("GET /matrículas/{} -> Response Body:\n{}\n", id, JsonUtil.objectToJson(entity));

        return ResponseEntity.ok(entity);
    }

    @GetMapping("alunos/{id}")
    public ResponseEntity<List<MatriculaEntity>> getIdAluno(@PathVariable Long id) {
        log.info("GET /matrículas/aluno/{} -> Início", id);

        List<MatriculaEntity> entity = service.buscarAlunoPorId(id);
        log.info("GET /matrículas/aluno/{} -> Encontrado", id);

        log.info("GET /matrículas/aluno/{} -> 200 OK", id);
        log.debug("GET /matrículas/aluno/{} -> Response Body:\n{}\n", id, JsonUtil.objectToJson(entity));

        return ResponseEntity.ok(entity);
    }

    @GetMapping("disciplina/{id}")
    public ResponseEntity<List<MatriculaEntity>> getIdDisciplina(@PathVariable Long id) {
        log.info("GET /matrículas/disciplina/{} -> Início", id);

        List<MatriculaEntity> entity = service.buscarDisciplinaPorId(id);
        log.info("GET /matrículas/disciplina/{} -> Encontrado", id);

        log.info("GET /matrículas/disciplina/{} -> 200 OK", id);
        log.debug("GET /matrículas/disciplina/{} -> Response Body:\n{}\n", id, JsonUtil.objectToJson(entity));

        return ResponseEntity.ok(entity);
    }

    @PostMapping
    public ResponseEntity<MatriculaEntity> post(@RequestBody MatriculaFiltro matriculaFiltro) {
        log.info("POST /matrículas");

        MatriculaEntity entity = service.criar(matriculaFiltro.getIdAluno(), matriculaFiltro.getIdDisciplina());
        log.info("POST /matrículas -> Cadastrado");

        log.debug("POST /matrículas -> Response Body:\n{}\n", JsonUtil.objectToJson(entity));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(entity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /matrículas/{}", id);
        service.excluir(id);

        log.info("DELETE /matrículas/{} -> Excluído", id);
        log.info("DELETE /matrículas/{} -> 204 NO CONTENT", id);
        return ResponseEntity.noContent().build();
    }

}
