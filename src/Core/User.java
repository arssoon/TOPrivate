package Core;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String imie;
    private String nazwisko;
    private String login;
    private String haslo;
    private int stanowisko;
    /*
    1 - burmistrz
    2 - radny
    */

    public User(int id, String imie, String nazwisko, String login, String haslo, int stanowisko) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.login = login;
        this.haslo = haslo;
        this.stanowisko = stanowisko;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public int getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(int stanowisko) {
        this.stanowisko = stanowisko;
    }

}