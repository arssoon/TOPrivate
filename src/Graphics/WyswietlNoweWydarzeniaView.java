package Graphics;

import Core.Client.ServerOperation;
import Core.ClientManager;
import Core.Events.CityEvent;
import Core.Events.CityEventType;
import Core.Events.DzienDziecka;
import Core.StatusUstawy;
import Core.StatusWniosek;
import Core.Wniosek;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class WyswietlNoweWydarzeniaView extends HBox{
    VBox mainVBox;
    ArrayList<HBox> listOfWydarzenia;
    Button cofnikButton;

    UIManager uiManager;

    public WyswietlNoweWydarzeniaView(UIManager uiManager){
        this.uiManager = uiManager;
        mainVBox = new VBox();
        listOfWydarzenia = new ArrayList<>();
        cofnikButton = new Button("Cofnij");
        getWydarzeniaFromDatabase();
        addStructure();
        setActionEvents();
    }

    private void getWydarzeniaFromDatabase(){
        System.out.println("chce pobrac wydarzenia z bazy");
        List<CityEvent> cityEvents = (List)ClientManager.clientSender.sendToServer(ServerOperation.getListaWydarzenByStatus,CityEventType.dzienDziecka);
        System.out.println("pobrano wydarzenia z bazy");
        for(CityEvent w:cityEvents)
            if(w.getStatusWydarzenia().equals(StatusWniosek.DO_ROZPATRZENIA))
                listOfWydarzenia.add(createWydarzenieElement(w));
    }


    private HBox createWydarzenieElement(CityEvent cityEvent){
        HBox element = new HBox();
        VBox dane = new VBox();
        VBox glosowanie = new VBox();


        Label startDate = new Label("Data rozpoczęcia: "+cityEvent.getStartDate());
        Label endDate = new Label("Data zakończenia: "+cityEvent.getEndDate());
        Label tytul = new Label("Tytuł: "+cityEvent.getTytul());
        Label opis = new Label("Opis: "+cityEvent.getOpis());
        Label dataWplywu = new Label("Data wpływu: "+cityEvent.getDataWplywu());
        Label statusWydarzenia = new Label("Status: "+cityEvent.getStatusWydarzenia());
        Label cityEventType = new Label("Typ: "+cityEvent.getCityEventType());

        dane.getChildren().add(tytul);
        dane.getChildren().add(opis);
        dane.getChildren().add(startDate);
        dane.getChildren().add(endDate);
        dane.getChildren().add(dataWplywu);
        dane.getChildren().add(statusWydarzenia);
        dane.getChildren().add(cityEventType);

        ToggleGroup group = new ToggleGroup();
        RadioButton takButton = new RadioButton("Zatwierdz");
        RadioButton nieButton = new RadioButton("Odrzuć");
        Button wykonajButton  = new Button("Wykonaj");
        takButton.setToggleGroup(group);
        nieButton.setToggleGroup(group);

        glosowanie.getChildren().add(takButton);
        glosowanie.getChildren().add(nieButton);
        glosowanie.getChildren().add(wykonajButton);
        glosowanie.setMinWidth(100);

        dane.setPrefWidth(450);

        element.getChildren().add(dane);
        element.getChildren().add(glosowanie);

        element.setStyle("-fx-background-color: Grey ");
        element.setBorder(new Border((new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))));
        element.setMinWidth(550);


        if(cityEvent instanceof DzienDziecka){
            Label liczbaDzieci = new Label("Liczba dzieci: "+((DzienDziecka) cityEvent).getLiczbaDzieci());
            Label maxLiczbaMiejsc = new Label("Liczba miejsc: "+((DzienDziecka) cityEvent).getMaxLiczbaMiejsc());
            Label liczbaWychowawcow = new Label("Liczba wychowawców: "+((DzienDziecka) cityEvent).getLiczbaWychowawcow());

            dane.getChildren().add(liczbaDzieci);
            dane.getChildren().add(maxLiczbaMiejsc);
            dane.getChildren().add(liczbaWychowawcow);
        }

        return element;
    }
    public void addStructure(){
        for(HBox hBox:listOfWydarzenia)
            mainVBox.getChildren().add(hBox);
        mainVBox.getChildren().add(cofnikButton);
        this.getChildren().add(mainVBox);
    }

    private void setActionEvents(){
        cofnikButton.setOnAction(event -> {
            uiManager.changeViewToMayorView();
        });
    }
}
