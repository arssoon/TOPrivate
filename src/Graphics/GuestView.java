package Graphics;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GuestView extends HBox {
    UIManager uiManager;


    Button addWniosekButton;
    Button addWydarzenieButton;
    Button wylogujButton;

    VBox mainVbox;


    public GuestView(UIManager uiManager){
        this.uiManager = uiManager;
        mainVbox = new VBox();
        addWniosekButton = new Button("Dodaj wniosek");
        addWydarzenieButton = new Button("Dodaj wydarzenie");
        wylogujButton = new Button("Wyloguj");

        addStructure();
        setActionEvents();
    }
    public void addStructure(){

        mainVbox.getChildren().add(addWniosekButton);
        mainVbox.getChildren().add(addWydarzenieButton);
        mainVbox.getChildren().add(wylogujButton);
        this.getChildren().add(mainVbox);


    }


    private void setActionEvents(){
        addWniosekButton.setOnAction(event -> {
            uiManager.changeViewToAddWniosekView();
        });
        addWydarzenieButton.setOnAction(event -> {
            uiManager.changeViewToAddWydarzenieView();
        });
        wylogujButton.setOnAction(event -> {
            uiManager.changeViewToLogin();
        });
    }
}
