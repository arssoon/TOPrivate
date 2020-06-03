package Core;

import Core.Client.Client;
import Core.Client.ClientSender;

public class ClientManager {

    public static ClientSender clientSender;
    public static Client client;

    public static User currentLoginUser;


    public ClientManager() {
        client  = new Client("localhost",4810);
        clientSender = client.getClientSender();
    }
}
