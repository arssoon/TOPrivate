package Core;

import java.util.Date;

public class GlosowanieWydarzenie {
    int id;
    boolean zakonczone;
    Date dataRozpoczecia;

    public GlosowanieWydarzenie(int id, boolean zakonczone, Date dataRozpoczecia) {
        this.id = id;
        this.zakonczone = zakonczone;
        this.dataRozpoczecia = dataRozpoczecia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isZakonczone() {
        return zakonczone;
    }

    public void setZakonczone(boolean zakonczone) {
        this.zakonczone = zakonczone;
    }

    public Date getDataRozpoczecia() {
        return dataRozpoczecia;
    }

    public void setDataRozpoczecia(Date dataRozpoczecia) {
        this.dataRozpoczecia = dataRozpoczecia;
    }
}
