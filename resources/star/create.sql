DROP TABLE g1_Zaliczenie CASCADE CONSTRAINTS ;

DROP TABLE g1_przedmiot CASCADE CONSTRAINTS ;

DROP TABLE g1_sala CASCADE CONSTRAINTS ;

DROP TABLE g1_student CASCADE CONSTRAINTS ;

DROP TABLE g1_termin CASCADE CONSTRAINTS ;

DROP TABLE g1_wykladowca CASCADE CONSTRAINTS ;

CREATE TABLE g1_przedmiot
  (
    id_przed NUMBER (5) NOT NULL ,
    nazwa    VARCHAR2 (25) ,
    typ      VARCHAR2 (15) CHECK (typ IN ('W', 'L','P'))
  ) ;
ALTER TABLE g1_przedmiot ADD CONSTRAINT g1_przedmiot_PK PRIMARY KEY ( id_przed ) ;

CREATE TABLE g1_sala
  ( id_sali NUMBER (4) NOT NULL , nazwa VARCHAR2 (15)
  ) ;
ALTER TABLE g1_sala ADD CONSTRAINT g1_sala_PK PRIMARY KEY ( id_sali ) ;

CREATE TABLE g1_student
  (
    nr_albumu NUMBER (7) NOT NULL ,
    nazwisko  VARCHAR2 (15) ,
    imie      VARCHAR2 (15) ,
    wiek      NUMBER (2) ,
    nr_gr     NUMBER (3) ,
    nazwa_gr  VARCHAR2 (15)
  ) ;
ALTER TABLE g1_student ADD CONSTRAINT g1_student_PK PRIMARY KEY ( nr_albumu ) ;

CREATE TABLE g1_termin
  (
    id_termin NUMBER (4) NOT NULL ,
    termin    VARCHAR2 (15) ,
    data      DATE
  ) ;
ALTER TABLE g1_termin ADD CONSTRAINT g1_termin_PK PRIMARY KEY ( id_termin ) ;


CREATE TABLE g1_wykladowca
  (
    nr_wykl  NUMBER (7) NOT NULL ,
    nazwisko VARCHAR2 (15) ,
    imie     VARCHAR2 (15) ,
    stopien  VARCHAR2 (15) CHECK (stopien IN ('mgr', 'dr','prof'))
  ) ;
ALTER TABLE g1_wykladowca ADD CONSTRAINT g1_wykladowca_PK PRIMARY KEY ( nr_wykl ) ;

CREATE TABLE g1_Zaliczenie
  (
    id_zal    NUMBER (7) NOT NULL ,
    id_przed  NUMBER (5) NOT NULL ,
    nr_albumu NUMBER (7) NOT NULL ,
    nr_wykl   NUMBER (7) NOT NULL ,
    id_sali   NUMBER (4) NOT NULL ,
    ocena     NUMBER (1) ,
    id_termin NUMBER (4) NOT NULL
  ) ;
ALTER TABLE g1_Zaliczenie ADD CONSTRAINT Zaliczenie_PK PRIMARY KEY ( id_zal ) ;

ALTER TABLE g1_Zaliczenie ADD CONSTRAINT g1_Zaliczenie_g1_sala_FK FOREIGN KEY ( id_sali ) REFERENCES g1_sala ( id_sali ) ;

ALTER TABLE g1_Zaliczenie ADD CONSTRAINT g1_Zaliczenie_g1_termin_FK FOREIGN KEY ( id_termin ) REFERENCES g1_termin ( id_termin ) ;

ALTER TABLE g1_Zaliczenie ADD CONSTRAINT g1_przedmiot_FK FOREIGN KEY ( id_przed ) REFERENCES g1_przedmiot ( id_przed ) ;

ALTER TABLE g1_Zaliczenie ADD CONSTRAINT g1_student_FK FOREIGN KEY ( nr_albumu ) REFERENCES g1_student ( nr_albumu ) ;

ALTER TABLE g1_Zaliczenie ADD CONSTRAINT g1_wykladowca_FK FOREIGN KEY ( nr_wykl ) REFERENCES g1_wykladowca ( nr_wykl ) ;