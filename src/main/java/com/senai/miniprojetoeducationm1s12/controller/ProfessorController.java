package com.senai.miniprojetoeducationm1s12.controller;

import com.senai.miniprojetoeducationm1s12.entity.ProfessorEntity;
import com.senai.miniprojetoeducationm1s12.service.ProfessorServiceImpl;
import com.senai.miniprojetoeducationm1s12.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("professores")
public class ProfessorController {
    private final ProfessorServiceImpl service;

    public ProfessorController(ProfessorServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProfessorEntity>> get() {
        log.info("GET /professores -> Início");

        List<ProfessorEntity> professores = service.buscarTodos();
        log.info("GET /professores -> {} registros", professores.size());

        log.info("GET /professores -> 200 OK");
        log.debug("GET /professores -> Response Body:\n{}\n", JsonUtil.objectToJson(professores));
        return ResponseEntity.ok(professores);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProfessorEntity> getId(@PathVariable Long id) {
        log.info("GET /professores/{} -> Início", id);

        ProfessorEntity entity = service.buscarPorId(id);
        log.info("GET /professores/{} -> Encontrado", id);

        log.info("GET /professores/{} -> 200 OK", id);
        log.debug("GET /professores/{} -> Response Body:\n{}\n", id, JsonUtil.objectToJson(entity));

        return ResponseEntity.ok(entity);
    }

    @PostMapping
    public ResponseEntity<ProfessorEntity> post(@RequestBody ProfessorEntity request) {
        log.info("POST /professores");
        
        ProfessorEntity professor = service.criar(request);
        log.info("POST /professores -> Cadastrado");

        log.debug("POST /professores -> Response Body:\n{}\n", JsonUtil.objectToJson(professor));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(professor);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProfessorEntity> put(@PathVariable Long id, @RequestBody ProfessorEntity request) {
        log.info("PUT /professores/{}", id);

        ProfessorEntity professor = service.alterar(id, request);
        log.info("PUT /professores/{} -> Atualizado", id);

        log.info("PUT /professores/{} -> 200 OK", id);
        log.debug("PUT /professores/{} -> Response Body:\n{}\n", id, JsonUtil.objectToJson(professor));
        return ResponseEntity.ok(professor);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /professores/{}", id);
        service.excluir(id);

        log.info("DELETE /professores/{} -> Excluído", id);
        log.info("DELETE /livros/{} -> 204 NO CONTENT", id);
        return ResponseEntity.noContent().build();
    }
}
