package Graphics;

import Core.ClientManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CouncilorView extends HBox {

    UIManager uiManager;

    Button dodajWniosekButton;
    Button dodajWydarzenieButton;
    Button wyswietlWszystkieWnioskiButton;
    Button wyswietlWszystkieWydarzeniaButton;
    Button wyswietlWnioskiDoZaglosowania;
    Button wylogujButton;
    Label loggedAt;
    VBox mainVBox;



    public CouncilorView(UIManager uiManager){
        this.uiManager = uiManager;

        mainVBox = new VBox();
        dodajWniosekButton = new Button("Dodaj wniosek");
        dodajWydarzenieButton = new Button("Dodaj wydarzenie");
        wyswietlWszystkieWnioskiButton = new Button("Wyswietl wszystkie wnioski");
        wyswietlWszystkieWydarzeniaButton = new Button("Wyswietl wszystkie wydarzenia");
        wyswietlWnioskiDoZaglosowania = new Button("Wyswietl wnioski do zaglosowania");
        loggedAt = new Label("Zalogowany jako: "+ClientManager.currentLoginUser.getImie()+ " "+ClientManager.currentLoginUser.getNazwisko());
        wylogujButton = new Button("Wyloguj");

        addStructure();
        setActionEvents();
    }
    public void addStructure(){
        mainVBox.getChildren().add(loggedAt);
        mainVBox.getChildren().add(dodajWniosekButton);
        mainVBox.getChildren().add(dodajWydarzenieButton);
        mainVBox.getChildren().add(wyswietlWszystkieWnioskiButton);
        mainVBox.getChildren().add(wyswietlWszystkieWydarzeniaButton);
        mainVBox.getChildren().add(wyswietlWnioskiDoZaglosowania);
        mainVBox.getChildren().add(wylogujButton);
        this.getChildren().add(mainVBox);
    }

    private void setActionEvents(){
        wylogujButton.setOnAction(event -> {
            ClientManager.currentLoginUser=null;
            uiManager.changeViewToLogin();
        });
    }
}
