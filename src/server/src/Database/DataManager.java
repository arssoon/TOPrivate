package server.src.Database;


import Core.*;
import Core.Events.CityEvent;
import Core.Events.CityEventType;
import Core.Events.DzienDziecka;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataManager {

    public static void addWniosek(Wniosek wniosek){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String sql = ("INSERT INTO WNIOSEK (id_wniosek,tytul,tresc,dataa,AUTOR,DATA_MODYFIKACJI,STATUS,NUMER_WNIOSKU,DATA_ROZPOCZECIA_GLOSOWANIA) VALUES ("
                +wniosek.getId()+",'"
                +wniosek.getTytul()+"','"
                +wniosek.getTresc()+"','"
                +sdf.format(wniosek.getDataWplywu())+"','"
                +wniosek.getAutor()+"','"
                +sdf.format(wniosek.getDataModyfikaci())+"','"
                +wniosek.getStatusWniosek()+"',"
                +wniosek.getNrWniosku()+",'"
                +sdf.format(wniosek.getDataRozpoczeciaGlosowania())+"')");
        DatabaseConnector.execute(sql);
    }
    public static void utworzGlosowanieWniosek(int id)
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String sql=("INSERT INTO GLOSOWANIE_USTAWA(id_glosowanie_ustawa,status,data_rozpoczecia,id_wniosek) VALUES("
                +(getLiczbaWnioskow()+1) + ",'"
                +StatusUstawy.ROZPOCZETA+ "','"
                +sdf.format(date)+ "','"
                +id+ "')");
        DatabaseConnector.execute(sql);

    }
    public static void zaglosujWniosek(int idWniosku, int idOsoby, Glos glos)
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String sql=("INSERT INTO Glosy_Wydarzenia(id_glosy_wydarzenia,dataa,glos,id_uzytkownik,id_wydarzenie) VALUES("
                +(getLiczbaGlosyWnioskow()+1)+",'"
                +sdf.format(date)+"','"
                +glos+"',"
                +idOsoby+","
                +idWniosku+ ")");
            DatabaseConnector.execute(sql);
    }


    public static Wniosek getWniosek(int id){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String sql = "SELECT * FROM Wniosek WHERE id_wniosek = "+id+"";
        ResultSet rs = DatabaseConnector.getResultSet(sql);
        Wniosek wniosek = null;

        try {
                rs.next();
                wniosek = new Wniosek(
                        rs.getInt("ID_WNIOSEK"),
                        rs.getString("TYTUL"),
                        rs.getString("TRESC"),
                        sdf.parse(rs.getString("DATAA")),
                        rs.getString("AUTOR"),
                        sdf.parse(rs.getString("data_modyfikacji")),
                        StatusWniosek.valueOf(rs.getString("STATUS")),
                        rs.getInt("numer_wniosku"),
                        sdf.parse(rs.getString("data_rozpoczecia_glosowania")));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return wniosek;
    }

    public static List<Wniosek> getListaWnioskowByStatus(StatusWniosek statusWniosek){
        String sql = "SELECT * FROM Wniosek WHERE status = '"+statusWniosek+"'";
        ResultSet rs = DatabaseConnector.getResultSet(sql);
        List<Wniosek> listaWnioskow = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        try {
            while (rs.next()) {
                listaWnioskow.add(new Wniosek(
                        rs.getInt("ID_WNIOSEK"),
                        rs.getString("tytul"),
                        rs.getString("tresc"),
                        (Date)sdf.parse(rs.getString("DATAA")),
                        rs.getString("autor"),
                        (Date)sdf.parse(rs.getString("DATA_MODYFIKACJI")),
                        StatusWniosek.valueOf(rs.getString("status")),
                        rs.getInt("numer_wniosku"),
                        (Date)sdf.parse(rs.getString("DATA_ROZPOCZECIA_GLOSOWANIA"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listaWnioskow;
    }
    public static void zmienStatusWnioskuZatwierdzone(int id,StatusWniosek statusWniosek)
    {
        String sql=("UPDATE WNIOSEK SET STATUS = '"+statusWniosek+"' WHERE ID_WNIOSEK = '"+id+"'");
        DatabaseConnector.execute(sql);
    }
    public static void zmienStatusWnioskuOdrzucone(int id,StatusWniosek statusWniosek)
    {
        String sql=("UPDATE WNIOSEK SET STATUS = '"+statusWniosek+"' WHERE ID_WNIOSEK = '"+id+"'");
        DatabaseConnector.execute(sql);
    }
    public static void zmienStatusWydarzenia(int id,StatusWniosek statusWniosek)
    {
        String sql=("UPDATE Wydarzenie SET STATUS = '"+statusWniosek+"' WHERE ID_WYDARZENIE = '"+id+"'");
        DatabaseConnector.execute(sql);
    }


    public static int getLiczbaWnioskow(){
        String sql = "select count(*) from Wniosek";
        ResultSet rs =DatabaseConnector.getResultSet(sql);
        int liczba=0;
        try {
            rs.next();
            liczba =  (rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liczba;
    }
    public static int getLiczbaGlosyWnioskow(){
        String sql = "select count(*) from Glosy_Wydarzenia";
        ResultSet rs =DatabaseConnector.getResultSet(sql);
        int liczba=0;
        try {
            rs.next();
            liczba =  (rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liczba;
    }
    public static int getLiczbaWydarzen(){
        String sql = "select count(*) from Wydarzenie";
        ResultSet rs =DatabaseConnector.getResultSet(sql);
        int liczba=0;
        try {
            rs.next();
            liczba =  (rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liczba;
    }

    public static User getUser(int id){
        User user = null;

        return user;
    }
    public static CityEvent getWydarzenie(int id){
        CityEvent cityEvent = null;



        switch (cityEvent.cityEventType){
            case dzienDziecka:

                //DzienDziecka dzienDziecka =  new DzienDziecka(new Date(1),new Date(2),CityEventType.dzienDziecka,"",1);
                //dzienDziecka.setLiczbaDzieci( Integer.parseInt(combineWydarzenieData(dzienDziecka.getDane())[1]));
        }

        return cityEvent;
    }

    public static void addWydarzenie(CityEvent cityEvent){

        switch (cityEvent.cityEventType) {
            case dzienDziecka:
                DzienDziecka dzienDziecka = (DzienDziecka) cityEvent;
                List<String> listOfDataAboutWydarzenie = new ArrayList();
                listOfDataAboutWydarzenie.add(Integer.toString(dzienDziecka.getLiczbaDzieci()));
                listOfDataAboutWydarzenie.add(Integer.toString(dzienDziecka.getMaxLiczbaMiejsc()));
                listOfDataAboutWydarzenie.add(Integer.toString(dzienDziecka.getLiczbaWychowawcow()));
                String dataAboutCityEvent = separateDataFromWydarzenie(listOfDataAboutWydarzenie);
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String sql = ("INSERT INTO WYDARZENIE (ID_WYDARZENIE,TYTUL,OPIS,DATA_WPLYWU,DATA_ROZPOCZECIA,DATA_ZAKONCZENIA,STATUS,TYP,DANE_U) VALUES ("
                        +cityEvent.getId()+",'"
                        +cityEvent.getTytul()+"','"
                        +cityEvent.getOpis()+"','"
                        +sdf.format(date)+"','"
                        +sdf.format(cityEvent.getStartDate())+"','"
                        +sdf.format(cityEvent.getEndDate())+"','"
                        +cityEvent.getStatusWydarzenia()+"','"
                        +cityEvent.cityEventType+"','"
                        +dataAboutCityEvent+"')");
                DatabaseConnector.execute(sql);

                break;
        }
    }

    public static List<CityEvent> getWydarzenia(CityEventType cityEventType){
        String sql = "SELECT * FROM Wydarzenie";
        ResultSet rs = DatabaseConnector.getResultSet(sql);
        List<CityEvent> listaWnioskow = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        switch (cityEventType){
            case dzienDziecka:
               try {
                    while (rs.next()) {
                        ArrayList<String> daneU = combineWydarzenieData(rs.getString("DANE_U"));
                        listaWnioskow.add(new DzienDziecka(
                                rs.getInt("ID_WYDARZENIE"),
                                (Date)sdf.parse(rs.getString("DATA_ROZPOCZECIA")),
                                (Date)sdf.parse(rs.getString("DATA_ZAKONCZENIA")),
                                rs.getString("TYTUL"),
                                rs.getString("OPIS"),
                                (Date)sdf.parse(rs.getString("DATA_WPLYWU")),
                                StatusWniosek.valueOf(rs.getString("STATUS")),
                                CityEventType.valueOf(rs.getString("TYP")),
                                Integer.parseInt(daneU.get(0)),
                                Integer.parseInt(daneU.get(1)),
                                Integer.parseInt(daneU.get(2))));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                   e.printStackTrace();
               }
                break;
        }
        return listaWnioskow;
    }

    public static User getUserByLogin(String login) {
       //addUsersToDatabase();
        String sql = "Select * From uzytkownik Where login = '"+login+"'";
        ResultSet rs = DatabaseConnector.getResultSet(sql);
        User user = null;
        try {
            rs.next();
            user =  new User(
                    rs.getInt("ID_UZYTKOWNIK"),
                    rs.getString("imie"),
                    rs.getString("nazwisko"),
                    rs.getString("login"),
                    rs.getString("haslo")/*,
                    rs.getInt("ID_STANOWISKA")*/
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }return user;
    }

    private static void addUsersToDatabase(){
        DatabaseConnector.execute("insert into uzytkownik values(1,'Krzysztof','Skalik','krzysiek','qwe')");
        DatabaseConnector.execute("insert into uzytkownik values(2,'Kamil','Niekurzak','kamil','qwe123')");
    }
    private static ArrayList<String> combineWydarzenieData(String data){
        ArrayList<String> daneU = new ArrayList<>();
        String[] temp = data.split("|");

        for(int i=0;i<temp.length;i++){
            if(!temp[i].equals("|"))
                daneU.add(temp[i]);
        }


                                                                                              //TODO: skonczyc!
        return daneU;
    }

    private static String separateDataFromWydarzenie(List<String> lista){
        String data = "";
        for(String e:lista)
            data +=e+"|";

        return data;
    }


}
