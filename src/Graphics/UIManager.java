package Graphics;

import Core.ClientManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class UIManager extends Application {

    StackPane root;
    Scene scene;
    HBox currentView;

    ConnectionView connectionView;
    LoginView loginView;
    GuestView guestView;
    MayorView mayorView;
    AddWydarzenieView addWydarzenieView;
    AddWniosekView addWniosekView;


    public static HBox lastView;



    @Override
    public void start(Stage primaryStage) throws Exception {
        createViews();

        currentView = connectionView;


        beginConfiguration(primaryStage);
    }

    private void createViews(){
        connectionView = new ConnectionView(this);
        loginView = new LoginView(this);
        guestView = new GuestView(this);
        //mayorView = new MayorView(this);
        addWniosekView = new AddWniosekView(this);
        addWydarzenieView = new AddWydarzenieView(this);
        ClientManager clientManager = new ClientManager();
    }


    private void beginConfiguration(Stage primaryStage){
        root = new StackPane();
        lastView= new HBox();
        lastView.getChildren().add(connectionView);
        root.getChildren().add(currentView);

        scene = new Scene(root, 1000, 750);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args){
        UIManager.launch();
    }

    public void changeViewToLogin(){
        currentView.getChildren().set(0,loginView);
    }
    public void changeViewToGuestView(){
        currentView.getChildren().set(0,guestView);
    }
    public void changeViewToAddWniosekView(){
        currentView.getChildren().set(0,addWniosekView);
    }
    public void changeViewToAddWydarzenieView(){
        currentView.getChildren().set(0,addWydarzenieView);
    }
    public void changeViewToWyswietlNoweWnioskiView(){
        currentView.getChildren().set(0,new WyswietlNoweWnioskiView(this));
    }
    public void changeViewToWyswietlNoweWydarzeniaView(){
        currentView.getChildren().set(0,new WyswietlNoweWydarzeniaView(this));
    }
    public void changeViewToMayorView(){
        currentView.getChildren().set(0,new MayorView(this));
    }
    public void changeViewToCouncilorView(){
        currentView.getChildren().set(0,new CouncilorView(this));
    }
    public void changeViewToWyswietlWszystkieWnioskiView(){
        currentView.getChildren().set(0,new WyswietlWszystkieWnioskiView(this));
    }
}
