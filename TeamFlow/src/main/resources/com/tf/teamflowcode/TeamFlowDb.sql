CREATE DATABASE progetto;

USE progetto;
insert into dipendente (nome, cognome, email, password, tipologia) values ('Giuseppe' , 'Garibaldi', 'giuseppe.garibaldi@gmail.com', '123', 'Impiegato A');

CREATE TABLE dipendente (
  matricola INT PRIMARY KEY auto_increment,
  nome VARCHAR(255),
  cognome VARCHAR(255),
  email VARCHAR(255),
  password VARCHAR(255),
  tipologia VARCHAR(255)
);

CREATE TABLE turno (
  id_turno INT auto_increment,
  data DATE,
  descrizione VARCHAR(255),
  t_matricola INT,
  ora_inizio TIME,
  ora_fine TIME,
  PRIMARY KEY (id_turno, t_matricola),
  FOREIGN KEY (t_matricola) REFERENCES dipendente(matricola) on delete cascade
);

drop table turno;
CREATE TABLE stipendio (
  anno_s int,
  mese_s int,
  s_matricola INT,
  ore_straordinario INT,
  importo double,
  PRIMARY KEY (anno_s, mese_s, s_matricola),
  FOREIGN KEY (s_matricola) REFERENCES dipendente(matricola) on delete cascade
);

CREATE TABLE permesso (
  id_turno INT auto_increment,
  p_matricola INT,
  data_p DATE,
  ora_inizio_turno VARCHAR(255),
  ora_fine_turno VARCHAR(255),
  motivazione VARCHAR(255),
  PRIMARY KEY (id_turno, p_matricola, data_p),
  FOREIGN KEY (p_matricola) REFERENCES dipendente(matricola) on delete cascade
);

select * from dipendente;
select * from turno;
select * from stipendio;
select * from permesso;

INSERT INTO dipendente (matricola, nome, cognome, email, password, tipologia) VALUES
  (1, 'Mario', 'Rossi', 'mario.rossi@gmail.com','MRss1','impiegato A'),
  (2, 'Anna', 'Bianchi', 'anna.bianchi@gmail.com','ANnb2', 'impiegato B'),
  (3, 'Giorgio', 'Verdi', 'giorgio.verdi@gmail.com','GIve3', 'Amministratore');
INSERT INTO turno (data, descrizione, t_matricola, ora_inizio, ora_fine) VALUES   ('2022-01-05', 'mattina', 3, '10:00:00', '13:00:00')

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

INSERT INTO stipendio (anno_s, mese_s, s_matricola, ore_straordinario, importo) VALUES
  (2022, 05, 1, 2, 40.00),
  (2022, 04, 1, 2, 150.00),
  (2022, 01, 2, 5, 100.00),
  (2022, 01, 1, 2, 50.00),
  (2022, 01, 3, 10, 200.00),
  (2022, 02, 1, 3, 75.00);