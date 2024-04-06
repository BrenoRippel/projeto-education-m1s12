CREATE TABLE IF NOT EXISTS Aluno
(
    id         BIGSERIAL PRIMARY KEY,
    nome       VARCHAR(150) NOT NULL,
    nascimento DATE
);

CREATE TABLE IF NOT EXISTS Professor
(
    id   BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL
);

CREATE TABLE IF NOT EXISTS Disciplina
(
    id           BIGSERIAL PRIMARY KEY,
    nome         VARCHAR(150) NOT NULL,
    professor_id BIGINT       NOT NULL,
    FOREIGN KEY (professor_id) REFERENCES Professor (id)
);

CREATE TABLE IF NOT EXISTS Disciplina_matricula
(
    id             BIGSERIAL PRIMARY KEY,
    aluno_id       BIGINT        NOT NULL,
    disciplina_id  BIGINT        NOT NULL,
    data_matricula DATE          NOT NULL DEFAULT now(),
    media_final    NUMERIC(5, 2) NOT NULL DEFAULT 0.00,
    FOREIGN KEY (aluno_id) REFERENCES Aluno (id),
    FOREIGN KEY (disciplina_id) REFERENCES Disciplina (id)
);

CREATE TABLE IF NOT EXISTS Notas
(
    id                      BIGSERIAL PRIMARY KEY,
    disciplina_matricula_id BIGINT         NOT NULL,
    disciplina_id           BIGINT         NOT NULL,
    nota                    NUMERIC(5, 2)  NOT NULL DEFAULT 0.00,
    coeficiente             NUMERIC(19, 6) NOT NULL DEFAULT 0.00,
    FOREIGN KEY (disciplina_matricula_id) REFERENCES Disciplina_matricula (id),
    FOREIGN KEY (disciplina_id) REFERENCES Disciplina (id)
);