package Core;



import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UstawaMain {

    Calendar aktualnaData;
    public UstawaMain() {
        aktualnaData=Calendar.getInstance();
    }

    public static void update(){

    }

    private void sprawdzDatyWnioskowGlosujacych(){/*
        List<Wniosek> lista = DataManager.getListaWnioskowByStatus(StatusWniosek.DO_POPRAWY);
        int idB;
        int nrW;
        idB = lista.get(0).getId();
        nrW = lista.get(0).getNrWniosku();
        for(Wniosek w:lista){
            if(toCalendar(w.getDataRozpoczeciaGlosowania()).get(Calendar.DAY_OF_YEAR)+7 >=aktualnaData.get(Calendar.DAY_OF_YEAR))

                DataManager.zmienStatusWniosku(w.getId(),StatusWniosek.ZARCHIWIZOWANY);

            idB = w.getId();
            nrW = w.getNrWniosku();
        }*/
    }

    private Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }





}
