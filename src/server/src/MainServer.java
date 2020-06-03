package server.src;


import server.src.Database.DatabaseConnector;

public class MainServer {

        public static void main(String[] args){
            DatabaseConnector databaseConnector;
            DatabaseConnector.connect();
            ServerListner serverListner = new ServerListner();
            System.out.println("Uruchamiam serwer...");
            serverListner.start();
        }


}
