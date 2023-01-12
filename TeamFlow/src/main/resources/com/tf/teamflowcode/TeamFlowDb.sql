CREATE DATABASE progetto;

USE progetto;

CREATE TABLE dipendente (
  matricola INT PRIMARY KEY,
  nome VARCHAR(255),
  cognome VARCHAR(255),
  email VARCHAR(255),
  password VARCHAR(255),
  tipologia VARCHAR(255)
);

CREATE TABLE turno (
  data DATE,
  descrizione VARCHAR(255),
  t_matricola INT,
  ora_inizio TIME,
  ora_fine TIME,
  PRIMARY KEY (data, t_matricola),
  FOREIGN KEY (t_matricola) REFERENCES dipendente(matricola)
);

CREATE TABLE stipendio (
  anno_s int,
  mese_s int,
  s_matricola INT,
  ore_straordinario INT,
  importo double,
  PRIMARY KEY (anno_s, mese_s, s_matricola),
  FOREIGN KEY (s_matricola) REFERENCES dipendente(matricola)
);

select * from dipendente;
select * from turno;
select * from stipendio;

INSERT INTO dipendente (matricola, nome, cognome, email, password, tipologia) VALUES
  (1, 'Mario', 'Rossi', 'mario.rossi@gmail.com','MRss1','impiegato A'),
  (2, 'Anna', 'Bianchi', 'anna.bianchi@gmail.com','ANnb2', 'impiegato B'),
  (3, 'Giorgio', 'Verdi', 'giorgio.verdi@gmail.com','GIve3', 'Amministratore');

INSERT INTO turno (data, descrizione, t_matricola, ora_inizio, ora_fine) VALUES
  ('2022-01-01', 'mattina', 1, '08:00:00', '16:00:00'),
  ('2022-01-02','pomeriggio', 1, '16:00:00', '00:00:00'),
  ('2022-01-03','notte', 1, '00:00:00', '8:00:00'),
  ('2022-01-01', 'mattina', 2, '08:00:00', '16:00:00'),
  ('2022-01-02','pomeriggio', 2, '16:00:00', '00:00:00'),
  ('2022-01-03','notte', 2, '00:00:00', '8:00:00'),
  ('2022-01-01', 'mattina', 3, '08:00:00', '16:00:00'),
  ('2022-01-02','pomeriggio', 3, '16:00:00', '00:00:00'),
  ('2022-01-03','notte', 3, '00:00:00', '8:00:00');

SELECT importo
FROM stipendio
WHERE s_matricola = 1
ORDER BY anno_s DESC, mese_s DESC;

INSERT INTO stipendio (anno_s, mese_s, s_matricola, ore_straordinario, importo) VALUES
  (2022, 05, 1, 2, 40.00),
  (2022, 04, 1, 2, 150.00),
  (2022, 01, 2, 5, 100.00),
  (2022, 01, 1, 2, 50.00),
  (2022, 01, 3, 10, 200.00),
  (2022, 02, 1, 3, 75.00);