package Graphics;

import Core.Client.ServerOperation;
import Core.ClientManager;
import Core.StatusWniosek;
import Core.Wniosek;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class WyswietlWszystkieWnioskiView extends HBox {
    VBox mainVBox;
    ArrayList<HBox> listOfWnioski;
    Button cofnikButton;

    UIManager uiManager;
    public WyswietlWszystkieWnioskiView(UIManager uiManager){
        this.uiManager = uiManager;
        mainVBox = new VBox();
        listOfWnioski = new ArrayList<>();
        cofnikButton = new Button("Cofnij");
        getWnioskiFromDatabase();
        addStructure();
        setActionEvents();
    }

    private void getWnioskiFromDatabase(){
        List<Wniosek> wnioski = (List)ClientManager.clientSender.sendToServer(ServerOperation.getListaWnioskowByStatus,StatusWniosek.DO_ROZPATRZENIA);
        wnioski.addAll((List)ClientManager.clientSender.sendToServer(ServerOperation.getListaWnioskowByStatus,StatusWniosek.DO_WERYFIKACJI));
        wnioski.addAll((List)ClientManager.clientSender.sendToServer(ServerOperation.getListaWnioskowByStatus,StatusWniosek.ODRZUCONE));
        wnioski.addAll((List)ClientManager.clientSender.sendToServer(ServerOperation.getListaWnioskowByStatus,StatusWniosek.PRZEGLOSOWANE));
        wnioski.addAll((List)ClientManager.clientSender.sendToServer(ServerOperation.getListaWnioskowByStatus,StatusWniosek.ZATWIERDZONE));
        wnioski.addAll((List)ClientManager.clientSender.sendToServer(ServerOperation.getListaWnioskowByStatus,StatusWniosek.ZREALOZIOWANE));
        for(Wniosek w:wnioski)
            listOfWnioski.add(createWniosekElement(w));
    }


    private HBox createWniosekElement(Wniosek wniosek){
        HBox element = new HBox();
        VBox dane = new VBox();
        VBox glosowanie = new VBox();


        Label tytul = new Label("Tytul: "+wniosek.getTytul());
        Label tresc = new Label("Tresc: "+wniosek.getTresc());
        Label data = new Label("Data wplywu: "+wniosek.getDataWplywu());
        Label autor = new Label("Autor: "+wniosek.getAutor());
        Label status = new Label("Status: "+wniosek.getStatusWniosek().toString());

        dane.getChildren().add(tytul);
        dane.getChildren().add(tresc);
        dane.getChildren().add(data);
        dane.getChildren().add(autor);
        dane.getChildren().add(status);


        dane.setPrefWidth(450);

        element.getChildren().add(dane);

        element.setStyle("-fx-background-color: Grey ");
        element.setBorder(new Border((new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))));
        element.setMinWidth(550);

        return element;
    }
    public void addStructure(){
        for(HBox hBox:listOfWnioski)
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
