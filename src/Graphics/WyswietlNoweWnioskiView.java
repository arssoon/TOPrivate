package Graphics;

import Core.Client.ServerOperation;
import Core.ClientManager;
import Core.StatusWniosek;
import Core.Wniosek;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import server.src.Database.DataManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WyswietlNoweWnioskiView extends HBox {
    VBox mainVBox;
    ArrayList<HBox>listOfWnioski;
    Button cofnikButton;
    Button wykonajButton;
    RadioButton takButton;
    RadioButton nieButton;
    ToggleGroup group;
    List<Wniosek> wnioski;
    String takString;
    String nieString;

    UIManager uiManager;
    public WyswietlNoweWnioskiView(UIManager uiManager){
        this.uiManager = uiManager;
        mainVBox = new VBox();
        listOfWnioski = new ArrayList<>();
        takString = "";
        nieString = "";
        cofnikButton = new Button("Cofnij");
        getWnioskiFromDatabase();
        addStructure();
        setActionEvents();
    }

    private void getWnioskiFromDatabase(){
        wnioski = (List)ClientManager.clientSender.sendToServer(ServerOperation.getListaWnioskowByStatus,StatusWniosek.DO_ROZPATRZENIA);
        for(Wniosek w:wnioski) {
            listOfWnioski.add(createWniosekElement(w));
        }
    }


    private HBox createWniosekElement(Wniosek wniosek){
        HBox element = new HBox();
        VBox dane = new VBox();
        VBox glosowanie = new VBox();

        Label nrWniosku = new Label("Nr wniosku: "+wniosek.getNrWniosku());
        Label tytul = new Label("Tytul: "+wniosek.getTytul());
        Label tresc = new Label("Tresc: "+wniosek.getTresc());
        Label data = new Label("Data wplywu: "+wniosek.getDataWplywu());
        Label autor = new Label("Autor: "+wniosek.getAutor());
        Label status = new Label("Status: "+wniosek.getStatusWniosek().toString());

        dane.getChildren().add(nrWniosku);
        dane.getChildren().add(tytul);
        dane.getChildren().add(tresc);
        dane.getChildren().add(data);
        dane.getChildren().add(autor);
        dane.getChildren().add(status);

        group = new ToggleGroup();
        takButton = new RadioButton("Zatwierdz");
        nieButton = new RadioButton("Odrzuć");
        wykonajButton  = new Button("Wykonaj");
        group.getToggles().add(takButton);
        group.getToggles().add(nieButton);

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
        for( HBox hBox1:listOfWnioski) {
            mainVBox.getChildren().add(hBox1);

            String cssLayout = "-fx-border-color: red;\n";
            hBox1.setStyle(cssLayout);

            hBox1.hoverProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                    System.out.println("Hover: " + oldValue + " -> " + newValue);
                    System.out.println("nrWNiosku: " + hBox1.getId());

                }
            });

            group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
                public void changed(ObservableValue<? extends Toggle> ov,
                                    Toggle toggle, Toggle new_toggle) {
                    if (new_toggle == null)
                        System.out.println("Togglebutton if");
                    else
                        System.out.println("Togglebutton else" + group.getSelectedToggle());

                }
            });
//        wykonajButton.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> System.out.println( e));
//        wykonajButton.addEventFilter(MouseEvent.MOUSE_EXITED, e -> System.out.println( e));
            hBox1.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
                if( e.getTarget() instanceof Button) {
                    System.out.println( e);
                }

            });
        }
        mainVBox.getChildren().add(cofnikButton);
        this.getChildren().add(mainVBox);
    }

    private void setActionEvents(){
        cofnikButton.setOnAction(event -> {
            uiManager.changeViewToMayorView();
        });



        takButton.setOnAction(event ->  {
            if(takButton.isSelected())
                takString = "ZATWIERDZONE";
            else
                nieString = null;
        });

        nieButton.setOnAction(event ->  {
            if(nieButton.isSelected())
                nieString = "ODRZUCONE";
            else
                nieString = null;
        });

        wykonajButton.setOnAction(event -> {
            int liczba = listOfWnioski.size();
            Wniosek wniosek = new Wniosek(liczba,null,null,new Date(),null,
                    new Date(),StatusWniosek.DO_ROZPATRZENIA,liczba,new Date());

            if(takString.equals("ZATWIERDZONE"))
            {
                System.out.println("Przed zmianą");
                ClientManager.clientSender.sendToServer(ServerOperation.zmienStatusWnioskuZatwierdzone, wniosek);
                System.out.println("Po zmianie");

            }
            else if(nieString.equals("ODRZUCONE")) {
                System.out.println("Przed zmianą");
                ClientManager.clientSender.sendToServer(ServerOperation.zmienStatusWnioskuOdrzucone, wniosek);
                System.out.println("Po zmianie");
            }
        });

    }


}
