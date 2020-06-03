package Core;


import java.util.Iterator;
import java.util.List;

public class ListaWnioskow implements Iterator {

    List<Wniosek> wnioski;
    int posistion = 0;

    public ListaWnioskow(StatusWniosek statusWniosek){
        //wnioski = DataManager.getListaWnioskowByStatus(statusWniosek);
    }


    @Override
    public boolean hasNext() {
        return posistion < wnioski.size() && wnioski.get(posistion) != null;
    }

    @Override
    public Wniosek next() {
        posistion++;
        return wnioski.get(posistion-1);
    }
}
