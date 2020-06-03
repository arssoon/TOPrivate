
import Core.Client.ServerOperation;
import Core.ClientManager;
import Core.StatusWniosek;
import Core.Wniosek;
import Graphics.UIManager;

import java.util.Date;

public class Main {
    public static void main(String[] args){
        System.out.println("nie mam sily");
        //ClientManager.clientSender.sendToServer(ServerOperation.addWniosek,new Wniosek(1,"qwe","qwe", new Date(),"qwe",new Date(),StatusWniosek.DO_ROZPATRZENIA,2,new Date(),0,0));
        //Wniosek wniosek = (Wniosek)ClientManager.clientSender.sendToServer(ServerOperation.getWniosek,1);
        //System.out.println(wniosek.getAutor());
        //UIManager uiManager = new UIManager();
        //UIManager.launch();
    }
}
