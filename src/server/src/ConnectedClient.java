package server.src;

import Core.Client.ServerOperation;
import Core.Events.CityEvent;
import Core.Events.CityEventType;
import Core.StatusWniosek;
import Core.User;
import Core.Wniosek;
import server.src.Database.DataManager;
import server.src.Database.DatabaseConnector;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ConnectedClient extends Thread {

    protected Socket socket;
    OutputStream outputStream;
    InputStream inputStream;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    public ConnectedClient(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
        try {
             outputStream = socket.getOutputStream();
             inputStream = socket.getInputStream();
             objectOutputStream = new ObjectOutputStream(outputStream);
             objectInputStream = new ObjectInputStream(inputStream);
        } catch (IOException e) {
            return;
        }
        while (true) {
            try {
                String read = (String) objectInputStream.readObject();
                ServerOperation serverOperation = ServerOperation.valueOf(read);
                if (serverOperation==ServerOperation.disconnect) {
                    socket.close();
                    DatabaseConnector.disconnect();
                    return;
                } else {
                    Object object = objectInputStream.readObject();
                    executeOperation(serverOperation,object);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void executeOperation(ServerOperation serverOperation,Object object){

        switch (serverOperation){
            case addWniosek:
                Wniosek wniosek = (Wniosek) object;
                DataManager.addWniosek(wniosek);
                try {
                    objectOutputStream.writeObject("nic");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case getWniosek:
                int id = (Integer) object;
                try {
                    objectOutputStream.writeObject(DataManager.getWniosek(id));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case getListaWnioskowByStatus:
                System.out.println("get LISTA WNIOSKOW BY STATUS");
                List<Wniosek> lista = DataManager.getListaWnioskowByStatus((StatusWniosek) object);
                try {
                    objectOutputStream.writeObject(lista);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case getLiczbaWnioskow:
                System.out.println("asfd");
                int liczba  = DataManager.getLiczbaWnioskow();
                try {
                    objectOutputStream.writeObject(liczba);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case addWydarzenie:
                CityEvent cityEvent = (CityEvent) object;
                System.out.println("asfd");
                DataManager.addWydarzenie(cityEvent);
                try {
                    objectOutputStream.writeObject("nic");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case getLiczbaWydarzen:
                System.out.println("asfd");
                int liczbaw  = DataManager.getLiczbaWydarzen();
                try {
                    objectOutputStream.writeObject(liczbaw);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case getUser:
                User user  = DataManager.getUserByLogin((String)object);
                try {
                    objectOutputStream.writeObject(user);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case getListaWydarzenByStatus:
                List<CityEvent> listace = DataManager.getWydarzenia((CityEventType) object);
                try {
                    objectOutputStream.writeObject(listace);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }


    }
}
