INSERT INTO Aluno (nome, nascimento)
VALUES ('João Silva', '2000-05-15'),
       ('Maria Oliveira', '1999-10-20'),
       ('Pedro Santos', '2001-03-12'),
       ('Ana Souza', '2002-07-08'),
       ('Lucas Pereira', '2000-12-30');

INSERT INTO Professor (nome)
VALUES ('Carlos Almeida'),
       ('Mariana Rodrigues'),
       ('Antônio Oliveira'),
       ('Patrícia Silva'),
       ('Rafaela Santos');

INSERT INTO Disciplina (nome, professor_id)
VALUES ('Matemática', 1),
       ('História', 2),
       ('Geografia', 3),
       ('Português', 4),
       ('Ciências', 5);

INSERT INTO Disciplina_matricula (aluno_id, disciplina_id, media_final)
VALUES (1, 1, 8.5),
       (2, 2, 7.8),
       (3, 3, 9.0),
       (4, 4, 6.5),
       (5, 5, 8.0);

INSERT INTO Notas (disciplina_matricula_id, disciplina_id, nota, coeficiente)
VALUES (1, 1, 8.5, 0.85),
       (2, 2, 7.8, 0.78),
       (3, 3, 9.0, 0.90),
       (4, 4, 6.5, 0.65),
       (5, 5, 8.0, 0.80);
