package com.senai.miniprojetoeducationm1s12.controller;

import com.senai.miniprojetoeducationm1s12.dto.NotaFiltro;
import com.senai.miniprojetoeducationm1s12.entity.MatriculaEntity;
import com.senai.miniprojetoeducationm1s12.entity.NotasEntity;
import com.senai.miniprojetoeducationm1s12.exceptions.error.NotFoundException;
import com.senai.miniprojetoeducationm1s12.repository.MatriculaRepository;
import com.senai.miniprojetoeducationm1s12.service.NotasServiceImpl;
import com.senai.miniprojetoeducationm1s12.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Slf4j
@RestController
@RequestMapping("notas")
public class NotasController {
    private final NotasServiceImpl notasServiceImpl;
    private final MatriculaRepository matriculaRepository;

    public NotasController(NotasServiceImpl notasServiceImpl, MatriculaRepository matriculaRepository) {
        this.notasServiceImpl = notasServiceImpl;
        this.matriculaRepository = matriculaRepository;
    }

    @GetMapping("{id}")
    public ResponseEntity<List<NotasEntity>> getNotasByMatriculaId(@PathVariable Long id) {
        log.info("GET /notas/{} -> Início", id);
        List<NotasEntity> entity = notasServiceImpl.buscarTodasPorMatriculaId(id);
        log.debug("GET /notas/{} -> Response Body:\n{}\n", id, JsonUtil.objectToJson(entity));

        log.info("GET /notas/{} -> 200 OK", id);
        return ResponseEntity.ok(entity);
    }

    @PostMapping
    public ResponseEntity<NotasEntity> adicionarNota(@RequestBody NotaFiltro request) {
        log.info("Buscando matricula por id");

        Optional<MatriculaEntity> matricula = matriculaRepository.findById(request.matricula_id());

        if (matricula.isEmpty()) {
            log.error("Buscando matricula por id ({}) -> Não foi encontrado!", request.matricula_id());
            throw new NotFoundException("Matricula ID:" + request.matricula_id() + " não encontrado!");
        }

        NotasEntity novaNota = new NotasEntity();

        novaNota.setMatricula(matricula.get());
        novaNota.setProfessor(matricula.get().getDisciplina().getProfessor());
        novaNota.setNota(request.nota());
        novaNota.setCoeficiente(request.coeficiente());
        notasServiceImpl.criar(novaNota);

        return ResponseEntity.status(HttpStatus.CREATED).body(novaNota);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /notas/{}", id);
        notasServiceImpl.excluir(id);

        log.info("DELETE /notas/{} -> Excluído", id);
        log.info("DELETE /notas/{} -> 204 NO CONTENT", id);
        return ResponseEntity.noContent().build();
    }

}
