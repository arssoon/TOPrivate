package Core.Client;

import java.io.*;

public class ClientSender {

    ObjectOutputStream outputStream;
    ObjectInputStream objectInputStream;


    public ClientSender(ObjectOutputStream outputStream,ObjectInputStream objectInputStream) {
        this.outputStream = outputStream;
        this.objectInputStream = objectInputStream;
    }

    public Object sendToServer(ServerOperation serverOperation,Object object) {

        try {
            outputStream.writeObject(serverOperation.toString());
            outputStream.writeObject(object);


            return objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            return objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
