DROP TABLE g2_data CASCADE CONSTRAINTS ;

DROP TABLE g2_grupa CASCADE CONSTRAINTS ;

DROP TABLE g2_przedmiot CASCADE CONSTRAINTS ;

DROP TABLE g2_sala CASCADE CONSTRAINTS ;

DROP TABLE g2_student CASCADE CONSTRAINTS ;

DROP TABLE g2_termin CASCADE CONSTRAINTS ;

DROP TABLE g2_wykladowca CASCADE CONSTRAINTS ;

DROP TABLE g2_zaliczenie CASCADE CONSTRAINTS ;

CREATE TABLE g2_data
  ( data_id NUMBER (5) NOT NULL , data_zal DATE
  ) ;
ALTER TABLE g2_data ADD CONSTRAINT g2_data_PK PRIMARY KEY ( data_id ) ;


CREATE TABLE g2_grupa
  ( nr_gr NUMBER (3) NOT NULL , nazwa VARCHAR2 (15)
  ) ;
ALTER TABLE g2_grupa ADD CONSTRAINT g2_grupa_PK PRIMARY KEY ( nr_gr ) ;


CREATE TABLE g2_przedmiot
  (
    id_przed NUMBER (5) NOT NULL ,
    nazwa    VARCHAR2 (25) ,
    typ      VARCHAR2 (15) CHECK (typ IN ('W', 'L','P'))
  ) ;
ALTER TABLE g2_przedmiot ADD CONSTRAINT g2_przedmiot_PK PRIMARY KEY ( id_przed ) ;


CREATE TABLE g2_sala
  ( id_sala NUMBER (4) NOT NULL , nazwa VARCHAR2 (15)
  ) ;
ALTER TABLE g2_sala ADD CONSTRAINT g2_sala_PK PRIMARY KEY ( id_sala ) ;


CREATE TABLE g2_student
  (
    nr_albumu NUMBER (5) NOT NULL ,
    nazwisko  VARCHAR2 (15) ,
    imie      VARCHAR2 (15) ,
    wiek      NUMBER (2) ,
    nr_gr     NUMBER (3) NOT NULL
  ) ;
ALTER TABLE g2_student ADD CONSTRAINT g2_student_PK PRIMARY KEY ( nr_albumu ) ;


CREATE TABLE g2_termin
  (
    id_termin NUMBER (5) NOT NULL ,
    termin    VARCHAR2 (15) ,
    data_id   NUMBER (5) NOT NULL
  ) ;
ALTER TABLE g2_termin ADD CONSTRAINT g2_termin_PK PRIMARY KEY ( id_termin ) ;


CREATE TABLE g2_wykladowca
  (
    nr_wykl  NUMBER (3) NOT NULL ,
    nazwisko VARCHAR2 (15) ,
    imie     VARCHAR2 (15) ,
    stopien  VARCHAR2 (15) CHECK (stopien IN ('mgr', 'dr','prof'))
  ) ;
ALTER TABLE g2_wykladowca ADD CONSTRAINT g2_wykladowca_PK PRIMARY KEY ( nr_wykl ) ;


CREATE TABLE g2_zaliczenie
  (
    id_zaliczenia NUMBER (6) NOT NULL ,
    id_przed      NUMBER (5) NOT NULL ,
    nr_albumu     NUMBER (5) NOT NULL ,
    nr_wykl       NUMBER (5) NOT NULL ,
    id_sala       NUMBER (4) NOT NULL ,
    ocena         NUMBER (1) ,
    id_termin     NUMBER (5) NOT NULL
  ) ;
ALTER TABLE g2_zaliczenie ADD CONSTRAINT g2_zaliczenie_PK PRIMARY KEY ( id_zaliczenia ) ;


ALTER TABLE g2_student ADD CONSTRAINT g2_student_g2_grupa_FK FOREIGN KEY ( nr_gr ) REFERENCES g2_grupa ( nr_gr ) ;

ALTER TABLE g2_termin ADD CONSTRAINT g2_termin_g2_data_FK FOREIGN KEY ( data_id ) REFERENCES g2_data ( data_id ) ;

ALTER TABLE g2_zaliczenie ADD CONSTRAINT g2_zaliczenie_g2_przedmiot_FK FOREIGN KEY ( id_przed ) REFERENCES g2_przedmiot ( id_przed ) ;

ALTER TABLE g2_zaliczenie ADD CONSTRAINT g2_zaliczenie_g2_sala_FK FOREIGN KEY ( id_sala ) REFERENCES g2_sala ( id_sala ) ;

ALTER TABLE g2_zaliczenie ADD CONSTRAINT g2_zaliczenie_g2_student_FK FOREIGN KEY ( nr_albumu ) REFERENCES g2_student ( nr_albumu ) ;

ALTER TABLE g2_zaliczenie ADD CONSTRAINT g2_zaliczenie_g2_termin_FK FOREIGN KEY ( id_termin ) REFERENCES g2_termin ( id_termin ) ;

ALTER TABLE g2_zaliczenie ADD CONSTRAINT g2_zaliczenie_g2_wykladowca_FK FOREIGN KEY ( nr_wykl ) REFERENCES g2_wykladowca ( nr_wykl ) ;
