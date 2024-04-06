package com.senai.miniprojetoeducationm1s12.controller;

import com.senai.miniprojetoeducationm1s12.entity.AlunoEntity;
import com.senai.miniprojetoeducationm1s12.entity.ProfessorEntity;
import com.senai.miniprojetoeducationm1s12.service.AlunoServiceImpl;
import com.senai.miniprojetoeducationm1s12.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("alunos")
public class AlunoController {
    private final AlunoServiceImpl alunoService;

    public AlunoController(AlunoServiceImpl alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public ResponseEntity<List<AlunoEntity>> get() {
        log.info("GET /alunos -> Início");

        List<AlunoEntity> alunos = alunoService.buscarTodos();

        log.info("GET /alunos -> {} total", alunos.size());

        log.info("GET /alunos -> 200 OK");
        log.debug("GET /alunos -> Response Body:\n{}\n", JsonUtil.objectToJson(alunos));
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("{id}")
    public ResponseEntity<AlunoEntity> getId(@PathVariable Long id) {
        log.info("GET /alunos/{} -> Início", id);

        AlunoEntity aluno = alunoService.buscarPorId(id);
        log.info("GET /alunos/{} -> Encontrado", id);

        log.info("GET /alunos/{} -> 200 OK", id);
        log.debug("GET /alunos/{} -> Response Body:\n{}\n", id, JsonUtil.objectToJson(aluno));

        return ResponseEntity.ok(aluno);
    }

    @PostMapping
    public ResponseEntity<AlunoEntity> post(@RequestBody AlunoEntity request) {
        log.info("POST /alunos");

        AlunoEntity aluno = alunoService.criar(request);
        log.info("POST /alunos -> Cadastrado");

        log.debug("POST /alunos -> Response Body:\n{}\n", JsonUtil.objectToJson(aluno));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(aluno);
    }

    @PutMapping("{id}")
    public ResponseEntity<AlunoEntity> put(@PathVariable Long id, @RequestBody AlunoEntity request) {
        log.info("PUT /alunos/{}", id);

        AlunoEntity aluno = alunoService.alterar(id, request);
        log.info("PUT /alunos/{} -> Atualizado", id);

        log.info("PUT /alunos/{} -> 200 OK", id);
        log.debug("PUT /alunos/{} -> Response Body:\n{}\n", id, JsonUtil.objectToJson(aluno));
        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /alunos/{}", id);
        alunoService.excluir(id);

        log.info("DELETE /alunos/{} -> Excluído", id);
        return ResponseEntity.noContent().build();
    }
}
