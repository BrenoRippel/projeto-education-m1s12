package com.senai.miniprojetoeducationm1s12.controller;

import com.senai.miniprojetoeducationm1s12.entity.DisciplinaEntity;
import com.senai.miniprojetoeducationm1s12.service.DisciplinaServiceImpl;

import com.senai.miniprojetoeducationm1s12.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("disciplina")
public class DisciplinaController {

    private final DisciplinaServiceImpl service;

    public DisciplinaController(DisciplinaServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaEntity>> get() {
        log.info("GET /disciplina -> buscando todas as disciplinas no Controller");
        List<DisciplinaEntity> disciplinas = service.getAll();
        log.debug("GET /disciplina -> Encontrado {} disciplinas", disciplinas.size());
        log.trace("GET /disciplina -> Disciplinas encontradas: \n{}", JsonUtil.objectToJson(disciplinas));
        return ResponseEntity.ok(disciplinas);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<DisciplinaEntity>> getId(@PathVariable Long id) {
        log.info("GET /disciplina/{} -> buscando disciplina de ID {}", id, id);
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping
    public ResponseEntity<DisciplinaEntity> post(@RequestBody DisciplinaEntity entity) {
        log.info("POST /disciplina -> Criando entidade");
        log.trace("POST /disciplina -> Criando a entidade \n{}", JsonUtil.objectToJson(entity));
        DisciplinaEntity response = service.create(entity);
        log.debug("POST /disciplina -> Entidade de ID {} criada", entity.getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<DisciplinaEntity> put(@PathVariable Long id, @RequestBody DisciplinaEntity entity) {
        log.info("PUT /disciplina{} -> Alterando dados de disciplina com ID {}", id, id);
        log.debug("PUT /disciplina{} -> Alterando dados da disciplina de ID {} para estes: \n{}", id, id, JsonUtil.objectToJson(entity));
        return ResponseEntity.ok(service.alter(id, entity));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /disciplina/{} -> Deletando entidade de ID {}", id, id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
