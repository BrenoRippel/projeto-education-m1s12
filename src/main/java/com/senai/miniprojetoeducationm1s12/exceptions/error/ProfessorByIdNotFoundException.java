package com.senai.miniprojetoeducationm1s12.exceptions.error;


public class ProfessorByIdNotFoundException extends NotFountException {
    public ProfessorByIdNotFoundException(Long id) {
        super("Professor não encontrado com id: " + id);
    }
}
