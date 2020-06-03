package Graphics;

import Core.Client.ServerOperation;
import Core.ClientManager;
import Core.StatusWniosek;
import Core.Wniosek;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class WyswietlNoweWnioskiView extends HBox {

    VBox mainVBox;
    ArrayList<HBox>listOfWnioski;
    Button cofnikButton;

    UIManager uiManager;
    public WyswietlNoweWnioskiView(UIManager uiManager){
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
