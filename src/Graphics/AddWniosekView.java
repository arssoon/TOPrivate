package Graphics;

import Core.Client.ServerOperation;
import Core.ClientManager;
import Core.StatusWniosek;
import Core.Wniosek;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddWniosekView  extends HBox {
    VBox mainVBox = new VBox();

    Label tytulLabel;
    Label trescLabel;
    Label autorLabel;

    TextArea tytulTextArea;
    TextArea trescTextArea;
    TextArea autorTextArea;


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    UIManager uiManager;
    Button wyslij;
    Button cofnij;



    public AddWniosekView(UIManager uiManager) {
        this.uiManager = uiManager;
        wyslij = new Button("Wyślij");
        tytulLabel = new Label("Wpisz tytuł:");
        trescLabel = new Label("Wpisz treść wniosku:");
        autorLabel = new Label("Wpisz autora wniosku:");
        tytulTextArea = new TextArea();
        trescTextArea = new TextArea();
        autorTextArea = new TextArea();
        cofnij = new Button("Cofnij");
        addStructure();
        setActionEvents();

    }

    public void addStructure(){
        mainVBox.getChildren().add(tytulLabel);
        mainVBox.getChildren().add(tytulTextArea);
        mainVBox.getChildren().add(trescLabel);
        mainVBox.getChildren().add(trescTextArea);
        mainVBox.getChildren().add(autorLabel);
        mainVBox.getChildren().add(autorTextArea);
        mainVBox.getChildren().add(wyslij);
        mainVBox.getChildren().add(cofnij);
        this.getChildren().add(mainVBox);
    }


    private void setActionEvents(){

        wyslij.setOnAction(event -> {
            int liczba = (int)ClientManager.clientSender.sendToServer(ServerOperation.getLiczbaWnioskow,null);
            Wniosek wniosek = new Wniosek(liczba+1,tytulTextArea.getText(),trescTextArea.getText(),new Date(),autorTextArea.getText(),new Date(),StatusWniosek.DO_ROZPATRZENIA,liczba+1,new Date());
            ClientManager.clientSender.sendToServer(ServerOperation.addWniosek,wniosek);
        });
        cofnij.setOnAction(event -> {
            uiManager.changeViewToGuestView();
        });
    }
}
