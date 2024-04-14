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

INSERT INTO Disciplina_matricula (aluno_id, disciplina_id, media_final, data_matricula)
VALUES (1, 1, 8.5, '1990-10-10'),
       (2, 2, 7.8, '2005-05-01'),
       (3, 3, 9.0, '2018-03-08'),
       (4, 4, 6.5, '2020-11-07'),
       (5, 5, 8.0, '2025-09-04');

INSERT INTO Notas (disciplina_matricula_id, professor_id, nota, coeficiente)
VALUES (1, 1, 8.5, 0.85),
       (2, 1, 7.8, 0.78),
       (3, 1, 9.0, 0.90),
       (4, 2, 6.5, 0.65),
       (5, 2, 8.0, 0.80);