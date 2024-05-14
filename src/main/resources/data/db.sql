CREATE TABLE IF NOT EXISTS tb_residencial (
id SERIAL PRIMARY KEY,
nome VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_lazer (
id SERIAL PRIMARY KEY,
tipo VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_residencial_lazer (
residencial_id SERIAL NOT NULL,
lazer_id SERIAL NOT NULL,
PRIMARY KEY (residencial_id, lazer_id),
FOREIGN KEY (residencial_id) REFERENCES tb_residencial (id) ON DELETE CASCADE,
FOREIGN KEY (lazer_id) REFERENCES tb_lazer (id) ON DELETE CASCADE
);