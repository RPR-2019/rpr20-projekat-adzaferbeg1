BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "uposlenikss" (
	"id"	INTEGER,
	"ime"	TEXT NOT NULL,
	"prezime"	TEXT NOT NULL,
	"titula"	TEXT NOT NULL,
	"username"	TEXT NOT NULL,
	"password"	TEXT NOT NULL,
	"email"	TEXT NOT NULL,
	"datum_zap"	TEXT NOT NULL,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "profesor" (
	"id"	INTEGER,
	"ime"	TEXT NOT NULL,
	"prezime"	TEXT NOT NULL,
	"datum_zap"	TEXT NOT NULL,
	"spol"	TEXT,
	"titula"	TEXT NOT NULL,
	"username"	TEXT NOT NULL,
	"password"	TEXT NOT NULL,
	"email"	TEXT NOT NULL,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "predmet_student" (
	"id"	INTEGER,
	"student"	TEXT,
	"predmet"	TEXT,
	"bodovi"	INTEGER,
	"ocjena"	TEXT,
	"ispit"	INTEGER,
	"zadaca"	INTEGER,
	"ostalo"	INTEGER,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "aktivnost" (
	"id"	INTEGER,
	"tekst"	TEXT,
	"datum"	TEXT,
	"tip"	TEXT,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "predmet" (
	"id"	INTEGER,
	"naziv"	TEXT,
	"ects"	TEXT,
	"studijska_godina"	INTEGER,
	"predavac"	TEXT,
	"odsjek"	TEXT,
	"semestar"	INTEGER,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "student" (
	"id"	INTEGER,
	"ime"	TEXT,
	"prezime"	TEXT,
	"datum_rodjenja"	TEXT,
	"spol"	TEXT,
	"broj_indeksa"	TEXT,
	"odsjek"	TEXT,
	"godina_studija"	INTEGER,
	"username"	TEXT,
	"password"	TEXT,
	"email"	TEXT,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "admin" (
	"id"	INTEGER,
	"username"	TEXT,
	"password"	TEXT,
	PRIMARY KEY("id")
);
INSERT INTO "uposlenikss" VALUES (1,'Marina','Krpić','dip. pravnik','marina','marina','marinak@gmail.com','03-08-2020');
INSERT INTO "profesor" VALUES (0,'Željko','Jurić','04-08-2020','Muški','red.prof.dr.','zjuric','zjuric','zjuric@gmail.com');
INSERT INTO "profesor" VALUES (1,'Vedran','Ljubović','27-07-2020','Muški','red.prof.dr.','vljubovic','vljubovic','vljubovic@gmail.com');
INSERT INTO "profesor" VALUES (2,'Selma','Hanjalić','28-07-2020','Ženski','red.prof.dr.','shanjalic','shanjalic','shanjalic@gmail.com');
INSERT INTO "profesor" VALUES (3,'Dinko','Osmanković','28-08-2020','Muški','red.prof.dr.','dosman','dosman','dosmankovic@gmail.com');
INSERT INTO "profesor" VALUES (4,'Naida','Mujić','09-06-2020','Ženski','red.prof.dr.','nmujic','nmujic','nmujic@gmail.com');
INSERT INTO "profesor" VALUES (5,'Samir','Ribić','04-05-2020','Muški','red.prof.dr.','megaribi','megaribi','sribic@gmail.com');
INSERT INTO "predmet_student" VALUES (1,'Ajla Džaferbegović','DM',78,'8',25,23,30);
INSERT INTO "predmet_student" VALUES (2,'Ajla Džaferbegović','TP',75,'8',35,25,15);
INSERT INTO "predmet_student" VALUES (3,'Adna Halilović','TP',0,'Nije ocijenjeno',0,0,0);
INSERT INTO "predmet_student" VALUES (4,'Zejd Čelenka','TP',75,'8',30,30,15);
INSERT INTO "predmet_student" VALUES (5,'Ajla Džaferbegović','IM1',65,'7',32,32,1);
INSERT INTO "predmet_student" VALUES (6,'Ajla Džaferbegović','IF1',65,'7',32,32,1);
INSERT INTO "predmet_student" VALUES (7,'Ajla Džaferbegović','OR',65,'7',32,32,1);
INSERT INTO "predmet_student" VALUES (8,'Ajla Džaferbegović','RPR',65,'7',32,32,1);
INSERT INTO "predmet_student" VALUES (9,'Ajla Džaferbegović','MLTI',65,'7',32,32,1);
INSERT INTO "predmet_student" VALUES (10,'Ajla Džaferbegović','OS',65,'7',32,32,1);
INSERT INTO "predmet_student" VALUES (11,'Zejd Čelenka','IF1',65,'7',32,32,1);
INSERT INTO "predmet_student" VALUES (12,'Zejd Čelenka','IF2',65,'7',32,32,1);
INSERT INTO "predmet_student" VALUES (13,'Zejd Čelenka','IM1',60,'6',27,20,13);
INSERT INTO "predmet_student" VALUES (14,'Zejd Čelenka','OR',65,'7',32,32,1);
INSERT INTO "predmet_student" VALUES (16,'Adna Halilović','IM1',0,'Nije ocijenjeno',0,0,0);
INSERT INTO "predmet_student" VALUES (17,'Adna Halilović','IF1',0,'Nije ocijenjeno',0,0,0);
INSERT INTO "predmet_student" VALUES (18,'Adna Halilović','OR',0,'Nije ocijenjeno',0,0,0);
INSERT INTO "predmet_student" VALUES (19,'Adna Halilović','MLTI',0,'Nije ocijenjeno',0,0,0);
INSERT INTO "predmet_student" VALUES (20,'Adna Halilović','OS',0,'Nije ocijenjeno',0,0,0);
INSERT INTO "aktivnost" VALUES (0,'IM1','18-08-2022','Zadaća');
INSERT INTO "aktivnost" VALUES (1,'Predavanje iz predmeta IM1 se neće održati.','05-08-2020','Obavijest');
INSERT INTO "aktivnost" VALUES (2,'Sutrašnje predavanje će biti pomjereno sa 14:00 na 15:00 h','12-08-2020','Obavijest');
INSERT INTO "aktivnost" VALUES (3,'IM1','16-09-2020','Ispit');
INSERT INTO "aktivnost" VALUES (4,'IM2','21-08-2020','Zadaća');
INSERT INTO "aktivnost" VALUES (5,'TP','03-09-2020','Zadaća');
INSERT INTO "aktivnost" VALUES (6,'DM','03-09-2020','Ispit');
INSERT INTO "aktivnost" VALUES (7,'RPR','02-03-2021','Zadaća');
INSERT INTO "predmet" VALUES (1,'IM1','7
',1,'Naida Mujić','Svi',1);
INSERT INTO "predmet" VALUES (2,'IF1','5',1,'Selma Hanjalić','Svi',1);
INSERT INTO "predmet" VALUES (3,'OR','6',1,'Vedran Ljubović','Svi',1);
INSERT INTO "predmet" VALUES (4,'MLTI','7',1,'Dinko Osmanković','Računarstvo i informatika',2);
INSERT INTO "predmet" VALUES (5,'OS','5',1,'Samir Ribić','Računarstvo i informatika',2);
INSERT INTO "predmet" VALUES (6,'TP','6',1,'Željko Jurić','Svi',2);
INSERT INTO "predmet" VALUES (7,'IF2','5',1,'Selma Hanjalić','Automatika i elektronika',2);
INSERT INTO "predmet" VALUES (8,'RPR','5',2,'Vedran Ljubović','Računarstvo i informatika',3);
INSERT INTO "predmet" VALUES (9,'DM','5',2,'Željko Jurić','Računarstvo i informatika',3);
INSERT INTO "student" VALUES (0,'Ajla','Džaferbegović','06-08-2020','Ženski','18593','Računarstvo i informatika',2,'ajla','ajla','adzaf@gmail.com');
INSERT INTO "student" VALUES (1,'Adna','Halilović','16-06-2020','Ženski','17689','Računarstvo i informatika',1,'adnah','adnah','adnah@gmail.com');
INSERT INTO "student" VALUES (2,'Zejd','Čelenka','04-05-2020','Muški','18699','Automatika i elektronika',3,'zcele','zcele','zcele@gmail.com');
INSERT INTO "student" VALUES (3,'Asim','Ćeman','13-11-1998','Muški','17654','Računarstvo i informatika',1,'asce','asce','asce@gmail.com');
INSERT INTO "admin" VALUES (1,'admin','admin');
COMMIT;
