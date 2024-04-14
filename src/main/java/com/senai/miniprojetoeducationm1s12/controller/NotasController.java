package com.senai.miniprojetoeducationm1s12.controller;

import com.senai.miniprojetoeducationm1s12.dto.NotaFiltro;
import com.senai.miniprojetoeducationm1s12.entity.MatriculaEntity;
import com.senai.miniprojetoeducationm1s12.entity.NotasEntity;
import com.senai.miniprojetoeducationm1s12.exceptions.error.NotFoundException;
import com.senai.miniprojetoeducationm1s12.repository.MatriculaRepository;
import com.senai.miniprojetoeducationm1s12.service.NotasServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Slf4j
@RestController
@RequestMapping("/notas")
public class NotasController {
    private final NotasServiceImpl notasServiceImpl;
    private final MatriculaRepository matriculaRepository;

    public NotasController(NotasServiceImpl notasServiceImpl, MatriculaRepository matriculaRepository) {
        this.notasServiceImpl = notasServiceImpl;
        this.matriculaRepository = matriculaRepository;
    }

    @GetMapping("/notas/{matriculaId}")
    public ResponseEntity<List<NotasEntity>> getNotasByMatriculaId(@PathVariable Long matriculaId) {
        List<NotasEntity> notas = notasServiceImpl.getNotasByMatriculaId(matriculaId);
        return ResponseEntity.ok(notas);
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

        // Calcular a media final na matricula feature card 7 depende

        return ResponseEntity.status(HttpStatus.CREATED).body(novaNota);
    }

    private double calcularMediaFinal(List<NotasEntity> notas) {
        double soma = 0;
        double somaCoeficientes = 0;
        for (NotasEntity nota : notas) {
            soma += nota.getNota() * nota.getCoeficiente();
            somaCoeficientes += nota.getCoeficiente();
        }
        return soma / somaCoeficientes;
    }

}
