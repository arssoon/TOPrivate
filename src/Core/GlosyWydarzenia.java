package Core;

import java.util.Date;

public class GlosyWydarzenia {
    int idGlosyWydarzenia;
    Date data;
    GlosWydarzenia glosWydarzenia;

    public GlosyWydarzenia(int idGlosyWydarzenia, Date data, GlosWydarzenia glosWydarzenia) {
        this.idGlosyWydarzenia = idGlosyWydarzenia;
        this.data = data;
        this.glosWydarzenia = glosWydarzenia;
    }

    public int getIdGlosyWydarzenia() {
        return idGlosyWydarzenia;
    }

    public void setIdGlosyWydarzenia(int idGlosyWydarzenia) {
        this.idGlosyWydarzenia = idGlosyWydarzenia;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public GlosWydarzenia getGlosWydarzenia() {
        return glosWydarzenia;
    }

    public void setGlosWydarzenia(GlosWydarzenia glosWydarzenia) {
        this.glosWydarzenia = glosWydarzenia;
    }

}
