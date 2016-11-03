

CREATE TABLE IF NOT EXISTS jogador 
(
	 id  INTEGER NOT NULL PRIMARY KEY   ,
	 posicao  INTEGER,
	 jogando  INTEGER,
	 motivacao  INTEGER,
	 habilidade  INTEGER,
	 condicionamento  INTEGER,
	 nome  TEXT
)
;

CREATE TABLE IF NOT EXISTS estadio 
(
	 id  INTEGER NOT NULL PRIMARY KEY   ,
	 capacidade  INTEGER,
	 nome  TEXT,
	 precoEntrada  REAL,
	 precoExpansao  REAL
)
;

CREATE TABLE IF NOT EXISTS clube 
(
	 id  INTEGER NOT NULL PRIMARY KEY   ,
	 forca  INTEGER,
	 nome  TEXT,

	CONSTRAINT  FK_Clube_Estadio  FOREIGN KEY ( id ) REFERENCES  estadio  ( id ) ON DELETE No Action ON UPDATE No Action,
	CONSTRAINT  FK_Clube_Jogador  FOREIGN KEY ( id ) REFERENCES  jogador  ( id ) ON DELETE No Action ON UPDATE No Action
)
;

CREATE TABLE IF NOT EXISTS jogo 
(
	 id  INTEGER NOT NULL,
	 golsLocal  INTEGER,
	 golsVisitante  INTEGER,
	 lucro  REAL,
	 vencedor  INTEGER,
	CONSTRAINT  PK_Jogo  PRIMARY KEY (id),
	CONSTRAINT  FK_Jogo_Campeonato  FOREIGN KEY ( id ) REFERENCES  campeonato  ( id ) ON DELETE No Action ON UPDATE No Action,
	CONSTRAINT  FK_Visitante_Clube  FOREIGN KEY ( id ) REFERENCES  clube  ( id ) ON DELETE No Action ON UPDATE No Action,
	CONSTRAINT  FK_Local_Clube  FOREIGN KEY ( id ) REFERENCES  clube  ( id ) ON DELETE No Action ON UPDATE No Action
)
;

CREATE TABLE IF NOT EXISTS loja 
(
	 id  INTEGER
)
;

CREATE TABLE IF NOT EXISTS campeonato 
(
	 id  INTEGER NOT NULL PRIMARY KEY);
