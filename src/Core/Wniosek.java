package Core;

import java.io.Serializable;
import java.util.Date;

public class Wniosek implements Serializable {
    int id;
    String tytul;
    String tresc;
    Date dataWplywu;
    String autor;
    Date dataModyfikaci;
    StatusWniosek statusWniosek;
    int nrWniosku;
    Date dataRozpoczeciaGlosowania;


    public Date getDataRozpoczeciaGlosowania() {
        return dataRozpoczeciaGlosowania;
    }

    public void setDataRozpoczeciaGlosowania(Date dataRozpoczeciaGlosowania) {
        this.dataRozpoczeciaGlosowania = dataRozpoczeciaGlosowania;
    }

    public int getNrWniosku() {
        return nrWniosku;
    }

    public void setNrWniosku(int nrWniosku) {
        this.nrWniosku = nrWniosku;
    }

    public Wniosek(int id, String tytul, String tresc, Date dataWplywu, String autor, Date dataModyfikaci, StatusWniosek statusWniosek, int nrWniosku, Date dataRozpoczeciaGlosowania) {
        this.id = id;
        this.tytul = tytul;
        this.tresc = tresc;
        this.dataWplywu = dataWplywu;
        this.autor = autor;
        this.dataModyfikaci = dataModyfikaci;
        this.statusWniosek = statusWniosek;
        this.nrWniosku = nrWniosku;
        this.dataRozpoczeciaGlosowania = dataRozpoczeciaGlosowania;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public Date getDataWplywu() {
        return dataWplywu;
    }

    public void setDataWplywu(Date dataWplywu) {
        this.dataWplywu = dataWplywu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getDataModyfikaci() {
        return dataModyfikaci;
    }

    public void setDataModyfikaci(Date dataModyfikaci) {
        this.dataModyfikaci = dataModyfikaci;
    }

    public StatusWniosek getStatusWniosek() {
        return statusWniosek;
    }

    public void setStatusWniosek(StatusWniosek statusWniosek) {
        this.statusWniosek = statusWniosek;
    }
}