package Core.Events;

import Core.StatusWniosek;

import java.util.Date;

public class DzienDziecka extends CityEvent {
    int liczbaDzieci;
    int maxLiczbaMiejsc;
    int liczbaWychowawcow;

    public DzienDziecka(int id, Date startDate, Date endDate, String tytul, String opis, Date dataWplywu, StatusWniosek statusWydarzenia, CityEventType cityEventType, int liczbaDzieci, int maxLiczbaMiejsc, int liczbaWychowawcow) {
        super(id, startDate, endDate, tytul, opis, dataWplywu, statusWydarzenia, cityEventType);
        this.liczbaDzieci = liczbaDzieci;
        this.maxLiczbaMiejsc = maxLiczbaMiejsc;
        this.liczbaWychowawcow = liczbaWychowawcow;
    }

    public int getLiczbaDzieci() {
        return liczbaDzieci;
    }

    public void setLiczbaDzieci(int liczbaDzieci) {
        this.liczbaDzieci = liczbaDzieci;
    }

    public int getMaxLiczbaMiejsc() {
        return maxLiczbaMiejsc;
    }

    public void setMaxLiczbaMiejsc(int maxLiczbaMiejsc) {
        this.maxLiczbaMiejsc = maxLiczbaMiejsc;
    }

    public int getLiczbaWychowawcow() {
        return liczbaWychowawcow;
    }

    public void setLiczbaWychowawcow(int liczbaWychowawcow) {
        this.liczbaWychowawcow = liczbaWychowawcow;
    }

    public int getLiczbaWolnychMiejsc(){

        return maxLiczbaMiejsc -= (liczbaDzieci + liczbaWychowawcow);
    }
}
