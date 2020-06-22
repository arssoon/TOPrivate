package Graphics;

import Core.Client.ServerOperation;
import Core.ClientManager;
import Core.User;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LoginView  extends HBox {
    UIManager uiManager;

    TextArea login = new TextArea();
    TextArea password = new TextArea();
    Label labelLogin;
    Label labelPassword;
    Button buttonContinueAsGuest;

    Button loginButton;
    VBox mainVbox;
    HBox hBoxWithButtons;


    public LoginView(UIManager uiManager){
        this.uiManager = uiManager;
        mainVbox = new VBox();
        labelLogin = new Label("Login: ");
        labelPassword = new Label("Haslo: ");
        loginButton = new Button("Zaloguj");
        buttonContinueAsGuest = new Button("Kontynuuj jako niezalogowany");
        hBoxWithButtons = new HBox();

        addStructure();
        setActionEvents();
    }
    public void addStructure(){


        login.setMaxHeight(15);
        password.setMaxHeight(15);

        loginButton.setMinSize(300,20);

        mainVbox.setSpacing(5);
        this.setPadding(new Insets(100,0,0,0));

        hBoxWithButtons.setSpacing(10);

        mainVbox.getChildren().add(labelLogin);
        mainVbox.getChildren().add(login);
        mainVbox.getChildren().add(labelPassword);
        mainVbox.getChildren().add(password);
        hBoxWithButtons.getChildren().add(loginButton);
        hBoxWithButtons.getChildren().add(buttonContinueAsGuest);
        mainVbox.getChildren().add(hBoxWithButtons);

        login.setText("krzysiek");
        password.setText("qwe");

        this.getChildren().add(mainVbox);


    }

    private void setActionEvents(){
        buttonContinueAsGuest.setOnAction(event -> {
            uiManager.changeViewToGuestView();
        });
        loginButton.setOnAction(event -> {
            login();
        });
    }

    private void login(){
        //User user =  (User)ClientManager.clientSender.sendToServer(ServerOperation.getUser,login.getText());
        User user  = new User(1,"Krzysztof","Skalik","krzysiek","qwe");
        System.out.println("Sprawdzam poprawnosc loginu i hasla i jest dobrze");
        if(password.getText().equals(user.getHaslo())){
            System.out.println("Zalogowano");
            ClientManager.currentLoginUser = user;
            uiManager.changeViewToMayorView();
            /*switch (user.getStanowisko()){
                case 1:
                    uiManager.changeViewToMayorView();
                    break;
                case 2:
                    uiManager.changeViewToCouncilorView();
                    break;
            }*/
        }
    }
}
