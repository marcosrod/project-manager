CREATE SEQUENCE IF NOT EXISTS project_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS task_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS project(
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255) NOT NULL,
    status VARCHAR(1) NOT NULL,
    fk_client BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS task(
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255) NOT NULL,
    status VARCHAR(1) NOT NULL,
    fk_project BIGINT NOT NULL,
    FOREIGN KEY(fk_project) REFERENCES project(id)
);