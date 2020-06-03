package Core;

import java.util.Date;

public class GlosowanieUstawa {
    int id;
    StatusUstawy statusUstawy;
    Date dataRozpoczecia;

    public GlosowanieUstawa(int id, StatusUstawy statusUstawy, Date dataRozpoczecia) {
        this.id = id;
        this.statusUstawy = statusUstawy;
        this.dataRozpoczecia = dataRozpoczecia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StatusUstawy getStatusUstawy() {
        return statusUstawy;
    }

    public void setStatusUstawy(StatusUstawy statusUstawy) {
        this.statusUstawy = statusUstawy;
    }

    public Date getDataRozpoczecia() {
        return dataRozpoczecia;
    }

    public void setDataRozpoczecia(Date dataRozpoczecia) {
        this.dataRozpoczecia = dataRozpoczecia;
    }
}
