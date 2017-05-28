DROP TABLE g1_Zaliczenie CASCADE CONSTRAINTS ;

DROP TABLE g1_przedmiot CASCADE CONSTRAINTS ;

DROP TABLE g1_student CASCADE CONSTRAINTS ;

DROP TABLE g1_wykladowca CASCADE CONSTRAINTS ;

CREATE TABLE g1_przedmiot
  (
    id_przed NUMBER (5) NOT NULL ,
    nazwa    VARCHAR2 (25) ,
    typ      VARCHAR2 (15)
  ) ;
ALTER TABLE g1_przedmiot ADD CONSTRAINT g1_przedmiot_PK PRIMARY KEY ( id_przed ) ;

CREATE TABLE g1_student
  (
    nr_albumu NUMBER (7) NOT NULL ,
    nazwisko  VARCHAR2 (15) ,
    imie      VARCHAR2 (15) ,
    wiek      NUMBER (2) 
  ) ;
ALTER TABLE g1_student ADD CONSTRAINT g1_student_PK PRIMARY KEY ( nr_albumu ) ;

CREATE TABLE g1_wykladowca
  (
    nr_wykl  NUMBER (7) NOT NULL ,
    nazwisko VARCHAR2 (15) ,
    imie     VARCHAR2 (15) ,
    stopien  VARCHAR2 (15)
  ) ;
ALTER TABLE g1_wykladowca ADD CONSTRAINT g1_wykladowca_PK PRIMARY KEY ( nr_wykl ) ;

CREATE TABLE g1_Zaliczenie
  (
    id_zal    NUMBER (7) NOT NULL ,
    id_przed  NUMBER (5) NOT NULL ,
    nr_albumu NUMBER (7) NOT NULL ,
    nr_wykl   NUMBER (7) NOT NULL ,
    ocena     NUMBER (1) ,
    termin    VARCHAR2 (15),
    nazwa_gr  VARCHAR2 (15),
    Data Date
  ) ;
ALTER TABLE g1_Zaliczenie ADD CONSTRAINT Zaliczenie_PK PRIMARY KEY ( id_zal ) ;

ALTER TABLE g1_Zaliczenie ADD CONSTRAINT g1_przedmiot_FK FOREIGN KEY ( id_przed ) REFERENCES g1_przedmiot ( id_przed ) ;

ALTER TABLE g1_Zaliczenie ADD CONSTRAINT g1_student_FK FOREIGN KEY ( nr_albumu ) REFERENCES g1_student ( nr_albumu ) ;

ALTER TABLE g1_Zaliczenie ADD CONSTRAINT g1_wykladowca_FK FOREIGN KEY ( nr_wykl ) REFERENCES g1_wykladowca ( nr_wykl ) ;