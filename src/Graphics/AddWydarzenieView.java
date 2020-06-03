package Graphics;

import Core.Client.Client;
import Core.Client.ServerOperation;
import Core.ClientManager;
import Core.Events.CityEvent;
import Core.Events.CityEventType;
import Core.Events.DzienDziecka;
import Core.StatusWniosek;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddWydarzenieView extends HBox {
    VBox mainVBox = new VBox();
    Label startDateLabel;
    Label endDateLabel;
    Label tytulLabel;
    Label opisLabel;
    TextArea startDateTextArea;
    TextArea endDateTextArea;
    TextArea tytulTextArea;
    TextArea opisTextArea;
    ComboBox comboBox;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    UIManager uiManager;
    VBox currentCityEvent;
    Button wyslij;

    //Dzien dziecka:
    TextArea liczbaDzieciTextArea;
    TextArea maxLiczbaMiejscTextArea;
    TextArea liczbaWychowawcowTextArea;
    Label liczbaDzieciLabel;
    Label maxLiczbaMiejscLabel;
    Label liczbaWychowawcowLabel;
    VBox dzienDzieckaVBox;
    Button cofnij;


    public AddWydarzenieView(UIManager uiManager) {
        currentCityEvent = new VBox();
        VBox blank  = new VBox();
        currentCityEvent.getChildren().add(blank);
        this.uiManager = uiManager;
        wyslij = new Button("Wyślij");
        startDateLabel = new Label("Wpisz date rozpoczęcia:");
        endDateLabel = new Label("Wpisz date zakończenia:");
        startDateTextArea = new TextArea("yyyy-mm-dd hh:mm:ss");
        endDateTextArea = new TextArea("yyyy-mm-dd hh:mm:ss");
        cofnij = new Button("Cofnij");
        opisLabel = new Label("Opis:");
        tytulLabel = new Label("Tytuł: ");
        opisTextArea = new TextArea();
        tytulTextArea = new TextArea();

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Dzień dziecka",
                        "Option 2",
                        "Option 3"
                );
        comboBox = new ComboBox(options);
        addStructure();
        setActionEvents();

    }

    public void addStructure(){
        mainVBox.getChildren().add(startDateLabel);
        mainVBox.getChildren().add(startDateTextArea);
        mainVBox.getChildren().add(endDateLabel);
        mainVBox.getChildren().add(endDateTextArea);
        mainVBox.getChildren().add(tytulLabel);
        mainVBox.getChildren().add(tytulTextArea);
        mainVBox.getChildren().add(opisLabel);
        mainVBox.getChildren().add(opisTextArea);
        mainVBox.getChildren().add(comboBox);
        mainVBox.getChildren().add(currentCityEvent);
        mainVBox.getChildren().add(wyslij);
        mainVBox.getChildren().add(cofnij);
        this.getChildren().add(mainVBox);
    }

    private void loadDzienDziecka(){
         dzienDzieckaVBox = new VBox();
         liczbaDzieciTextArea = new TextArea();
         maxLiczbaMiejscTextArea = new TextArea();
         liczbaWychowawcowTextArea = new TextArea();
         liczbaDzieciLabel = new Label("Podaj liczbe dzieci");
         maxLiczbaMiejscLabel = new Label("Podaj maksymalna ilosc miejsc:");
         liczbaWychowawcowLabel= new Label("Podaj liczbe wychowawcow:");
         dzienDzieckaVBox.getChildren().add(liczbaDzieciLabel);
         dzienDzieckaVBox.getChildren().add(liczbaDzieciTextArea);
         dzienDzieckaVBox.getChildren().add(maxLiczbaMiejscLabel);
         dzienDzieckaVBox.getChildren().add(maxLiczbaMiejscTextArea);
         dzienDzieckaVBox.getChildren().add(liczbaWychowawcowLabel);
         dzienDzieckaVBox.getChildren().add(liczbaWychowawcowTextArea);
    }

    private void setActionEvents(){
        comboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                switch (t1){
                    case "Dzień dziecka":
                        loadDzienDziecka();

                        currentCityEvent.getChildren().set(0,dzienDzieckaVBox);
                        break;
                }
            }
        });

        wyslij.setOnAction(event -> {
            CityEvent cityEvent = null;
            int liczbaWnioskow = (int)ClientManager.clientSender.sendToServer(ServerOperation.getLiczbaWydarzen,null);
            switch (comboBox.getSelectionModel().getSelectedItem().toString()){
                case "Dzień dziecka":
                    try {
                        cityEvent = new DzienDziecka(liczbaWnioskow,
                                sdf.parse(startDateTextArea.getText()),
                                sdf.parse(endDateTextArea.getText()),
                                tytulTextArea.getText(),
                                opisTextArea.getText(),
                                new Date(),
                                StatusWniosek.DO_ROZPATRZENIA,
                                CityEventType.dzienDziecka,
                                Integer.parseInt(liczbaDzieciTextArea.getText()),
                                Integer.parseInt(maxLiczbaMiejscTextArea.getText()),
                                Integer.parseInt(liczbaWychowawcowTextArea.getText()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;

            }
            ClientManager.clientSender.sendToServer(ServerOperation.addWydarzenie,cityEvent);
            uiManager.changeViewToGuestView();
        });
        cofnij.setOnAction(event -> {
            uiManager.changeViewToGuestView();
        });

    }

}
