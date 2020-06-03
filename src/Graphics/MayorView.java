package Graphics;

import Core.ClientManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MayorView extends HBox {
    UIManager uiManager;

    Button wyswietlNoweWydarzeniaButton;
    Button wyswietlNoweWnioskiButton;
    Button wyswietlWszystkieWnioskiButton;
    Button wyswietlWszystkieWydarzeniaButton;
    Button wylogujButton;
    Label loggedAt;
    VBox mainVBox;



    public MayorView(UIManager uiManager){
        this.uiManager = uiManager;
        loggedAt = new Label("Zalogowany jako: "+ClientManager.currentLoginUser.getImie()+ " "+ClientManager.currentLoginUser.getNazwisko());
        mainVBox = new VBox();
        wyswietlNoweWnioskiButton = new Button("Wyświetl nowe wnioski");
        wyswietlNoweWydarzeniaButton = new Button("Wyświetl nowe wydarzenia");
        wyswietlWszystkieWnioskiButton = new Button("Wyswietl wszystkie wnioski");
        wyswietlWszystkieWydarzeniaButton = new Button("Wyswietl wszystkie wydarzenia");
        wylogujButton = new Button("Wyloguj");

        addStructure();
        setActionEvents();
    }
    public void addStructure(){
        mainVBox.getChildren().add(loggedAt);
        mainVBox.getChildren().add(wyswietlNoweWnioskiButton);
        mainVBox.getChildren().add(wyswietlNoweWydarzeniaButton);
        mainVBox.getChildren().add(wyswietlWszystkieWnioskiButton);
        mainVBox.getChildren().add(wyswietlWszystkieWydarzeniaButton);
        mainVBox.getChildren().add(wylogujButton);
        this.getChildren().add(mainVBox);
    }

    private void setActionEvents(){
        wyswietlNoweWnioskiButton.setOnAction(event -> {
            uiManager.changeViewToWyswietlNoweWnioskiView();
        });
        wylogujButton.setOnAction(event -> {
            ClientManager.currentLoginUser=null;
            uiManager.changeViewToLogin();
        });
        wyswietlNoweWydarzeniaButton.setOnAction(event -> {
            uiManager.changeViewToWyswietlNoweWydarzeniaView();
        });
        wyswietlWszystkieWnioskiButton.setOnAction(event -> uiManager.changeViewToWyswietlWszystkieWnioskiView());
    }
}
