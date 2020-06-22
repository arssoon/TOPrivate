package Core.Events;

import Core.StatusWniosek;
import java.io.Serializable;
import java.util.Date;

public abstract class CityEvent implements Serializable {
    int id;
    Date startDate;
    Date endDate;
    String tytul;
    String opis;
    Date dataWplywu;
    StatusWniosek statusWydarzenia;
public CityEventType cityEventType;


    public int getIloscMiejsc(){
        return 0;
    }

    public String getOpis() {
        return opis;
    }

    public String getTytul() {

        return tytul;
    }

    public Date getDataWplywu() {
        return dataWplywu;
    }

    public StatusWniosek getStatusWydarzenia() {
        return statusWydarzenia;
    }

    public CityEvent(int id, Date startDate, Date endDate, String tytul, String opis, Date dataWplywu, StatusWniosek statusWydarzenia, CityEventType cityEventType) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tytul = tytul;
        this.opis = opis;
        this.dataWplywu = dataWplywu;
        this.statusWydarzenia = statusWydarzenia;
        this.cityEventType = cityEventType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public CityEventType getCityEventType() {
        return cityEventType;
    }

    public int getId() {

        return id;
    }
}
