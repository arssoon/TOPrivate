package Graphics;

import Core.Client.ServerOperation;
import Core.ClientManager;
import Core.Glos;
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

public class VotingViev extends HBox {
    UIManager uiManager;
    VBox mainVBox;
    ArrayList<HBox> listOfWnioski;
    Button cofnikButton;
    Button wykonajButton;
    RadioButton takButton;
    RadioButton nieButton;
    List<Wniosek> wnioski;
    public VotingViev(UIManager uiManager){
        this.uiManager=uiManager;
        mainVBox = new VBox();
        listOfWnioski = new ArrayList<>();
        cofnikButton = new Button("Cofnij");
        getWnioskiFromDatabase();
        addStructure();
        setActionEvents();

    }
    private void getWnioskiFromDatabase(){
        wnioski = (List) ClientManager.clientSender.sendToServer(ServerOperation.getListaWnioskowByStatus, StatusWniosek.ZATWIERDZONE);
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
        takButton = new RadioButton("Za");
        nieButton = new RadioButton("Przeciw");
        wykonajButton = new Button("Wykonaj");
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
        wykonajButton.setOnAction(event -> {
            if(takButton.isSelected()){
                wnioski = (List) ClientManager.clientSender.sendToServer(ServerOperation.wniosekZaglosuj, Glos.ZA);
            }
                }
                );
    }

}
