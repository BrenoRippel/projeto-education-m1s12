package com.senai.miniprojetoeducationm1s12.exceptions.error;

public class NotaByIdNotFoundException extends NotFoundException  {
    public NotaByIdNotFoundException(Long id) {
        super("Nota não encontrado com id: " + id);
    }
}