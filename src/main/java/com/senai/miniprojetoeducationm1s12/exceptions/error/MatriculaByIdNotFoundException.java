package com.senai.miniprojetoeducationm1s12.exceptions.error;

public class MatriculaByIdNotFoundException extends NotFoundException {
    public MatriculaByIdNotFoundException(Long id) {
        super("Matrícula não encontrado com id: " + id);
    }
}
