DROP TABLE IF EXISTS 'campeonato'
;

DROP TABLE IF EXISTS 'estadio'
;

DROP TABLE IF EXISTS 'clube'
;

DROP TABLE IF EXISTS 'jogador'
;

DROP TABLE IF EXISTS 'jogo'
;

DROP TABLE IF EXISTS 'loja'
;

CREATE TABLE 'campeonato'
(
	'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT
)
;

CREATE TABLE 'estadio'
(
	'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	'capacidade' INTEGER,
	'nome' TEXT,
	'precoEntrada' REAL,
	'precoExpansao' REAL
)
;

CREATE TABLE 'clube'
(
	'id' INTEGER NOT NULL PRIMARY KEY,
	'forca' INTEGER,
	'nome' TEXT,

	CONSTRAINT 'FK_Clube_Estadio' FOREIGN KEY ('id') REFERENCES 'estadio' ('id') ON DELETE No Action ON UPDATE No Action
)
;

CREATE TABLE 'jogador'
(
	'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	'posicao' INTEGER,
	'jogando' INTEGER,
	'motivacao' INTEGER,
	'habilidade' INTEGER,
	'condicionamento' INTEGER,
	'nome' TEXT,

	CONSTRAINT 'FK_jogador_clube' FOREIGN KEY ('id') REFERENCES 'clube' ('id') ON DELETE No Action ON UPDATE No Action
)
;

CREATE TABLE 'jogo'
(
	'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	'golsLocal' INTEGER,
	'golsVisitante' INTEGER,
	'lucro' REAL,
	'vencedor' INTEGER,

	CONSTRAINT 'FK_jogo_campeonato' FOREIGN KEY ('id') REFERENCES 'campeonato' ('id') ON DELETE No Action ON UPDATE No Action,
	CONSTRAINT 'FK_Visitante_Clube' FOREIGN KEY ('id') REFERENCES 'clube' ('id') ON DELETE No Action ON UPDATE No Action,
	CONSTRAINT 'FK_Local_Clube' FOREIGN KEY ('id') REFERENCES 'clube' ('id') ON DELETE No Action ON UPDATE No Action
)
;

CREATE TABLE 'loja'
(
	'id' INTEGER
)
;
