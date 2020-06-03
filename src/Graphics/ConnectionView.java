package Graphics;

import Core.Client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ConnectionView extends HBox {
    UIManager uiManager;

    TextArea ip = new TextArea();
    TextArea port = new TextArea();
    Label labelIp;
    Label labelPort;

    Button connectButton;
    VBox mainVbox;


    public ConnectionView(UIManager uiManager){
        this.uiManager = uiManager;
        mainVbox = new VBox();
        labelIp = new Label("IP: ");
        labelPort = new Label("PORT: ");
        connectButton = new Button("Polacz");

        ip.setText("localhost");
        port.setText("4810");

        addStructure();
        setActionEvents();
    }
    public void addStructure(){


        ip.setMaxHeight(15);
        port.setMaxHeight(15);

        connectButton.setMinSize(500,20);

        mainVbox.setSpacing(5);
        this.setPadding(new Insets(100,0,0,250));

        mainVbox.getChildren().add(labelIp);
        mainVbox.getChildren().add(ip);
        mainVbox.getChildren().add(labelPort);
        mainVbox.getChildren().add(port);
        mainVbox.getChildren().add(connectButton);
        this.getChildren().add(mainVbox);


    }


    private void setActionEvents(){
        connectButton.setOnAction(event -> {
            System.out.println("CLICK");
            Client client = new Client(ip.getText(),Integer.parseInt(port.getText()));
            uiManager.changeViewToLogin();
        });
    }
}
