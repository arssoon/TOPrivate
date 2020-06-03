CREATE TABLE Stanowisko(
id_stanowiska NUMBER(3) PRIMARY KEY,
nazwa VARCHAR2(60)
);

CREATE TABLE Pracownik(
id_pracownik NUMBER(3) Primary Key,
id_stanowiska NUMBER(3) REFERENCES Stanowisko(id_stanowiska),
id_uzytkownik NUMBER(6) REFERENCES uzytkownik(id_uzytkownik)
);

CREATE TABLE Glosy_Wydarzenia(
id_glosy_wydarzenia NUMBER(4) PRIMARY KEY,
dataa VARCHAR2(40),
glos VARCHAR2(20),
id_uzytkownik NUMBER(6) REFERENCES uzytkownik(id_uzytkownik),
id_wydarzenie NUMBER(10) REFERENCES Wydarzenie(id_wydarzenie)
);

CREATE TABLE Glosy_Ustawa(
id_glosy_ustawa NUMBER(4) PRIMARY KEY,
dataa VARCHAR2(40),
glos VARCHAR2(20),
id_uzytkownik NUMBER(6) REFERENCES uzytkownik(id_uzytkownik),
id_wniosek NUMBER(10) REFERENCES Wniosek(id_wniosek)
);

CREATE TABLE Glosowanie_Wydarzenie(
id_glosowanie_wydarzenie NUMBER(10) PRIMARY KEY,
status VARCHAR2(30),
data_rozpoczecia VARCHAR(40),
id_wydarzenie NUMBER(10) REFERENCES Wydarzenie(id_wydarzenie)
);

CREATE TABLE Glosowanie_Ustawa(
id_glosowanie_ustawa NUMBER(10) PRIMARY KEY,
status VARCHAR2(30),
data_rozpoczecia VARCHAR(40),
id_wniosek NUMBER(10) REFERENCES Wniosek(id_wniosek)
);

CREATE TABLE Wniosek(
id_wniosek NUMBER(10) PRIMARY KEY,
tytul VARCHAR2(100),
tresc VARCHAR2(2000),
dataa VARCHAR2(40),
autor VARCHAR2(60),
data_modyfikacji VARCHAR2(40),
status VARCHAR2(30),
numer_wniosku Number(10),
data_rozpoczecia_glosowania VARCHAR2(40)
);

CREATE TABLE Wydarzenie (
id_wydarzenie NUMBER(10) PRIMARY KEY,
tytul VARCHAR2(100),
opis VARCHAR2(1000),
data_wplywu VARCHAR2(40),
data_rozpoczecia VARCHAR2(40),
data_zakonczenia VARCHAR2(40),
status VARCHAR2(30),
typ VARCHAR(50),
dane_u VARCHAR2(100)
);

CREATE  TABLE uzytkownik (

id_uzytkownik NUMBER(6) PRIMARY KEY,
imie VARCHAR2(30),
nazwisko VARCHAR2(30),
login VARCHAR2(40),
haslo VARCHAR2(40)
);
