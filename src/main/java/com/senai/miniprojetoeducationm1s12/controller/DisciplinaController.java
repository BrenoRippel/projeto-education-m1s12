package com.senai.miniprojetoeducationm1s12.controller;

import com.senai.miniprojetoeducationm1s12.entity.DisciplinaEntity;
import com.senai.miniprojetoeducationm1s12.service.DisciplinaServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("disciplina")
public class DisciplinaController {

    Logger logger = LoggerFactory.getLogger(DisciplinaController.class);
    private final DisciplinaServiceImpl service;

    public DisciplinaController(DisciplinaServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaEntity>> get() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<DisciplinaEntity>> getId(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping
    public ResponseEntity<DisciplinaEntity> post(@RequestBody DisciplinaEntity entity) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(entity));
    }

    @PutMapping("{id}")
    public ResponseEntity<DisciplinaEntity> put(@PathVariable Long id, @RequestBody DisciplinaEntity entity) {
        return ResponseEntity.ok(service.alter(id, entity));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
