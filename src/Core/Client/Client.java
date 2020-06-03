package Core.Client;

import java.io.*;
import java.net.*;

public class Client {
    private  int port;
    private  String ip;

    private ClientSender clientSender;


    public int getPort() {
        return port;
    }

    public String getIp() {
        return ip;
    }

    public ClientSender getClientSender() {
        return clientSender;
    }


    public  Client(String ip, int port) {
        this.ip = ip;
        this.port = port;

        try
        {
            Socket socket = new Socket(ip, port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStream  outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            clientSender = new ClientSender(objectOutputStream,objectInputStream);

        }
        catch (Exception e)
        {
            System.err.println(e);
        }

    }
}
