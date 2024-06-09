package org.sani;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private final String serverName;
    private final int serverPort;
    private String userName;

    public Client(String serverName, int serverPort) {
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    public void start(){
        try{
            System.out.println("Enter your username: ");
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            userName = console.readLine();

            Socket socket = new Socket(serverName, serverPort);
            System.out.println("connection successful");

            ServerListener serverListener = new ServerListener(socket);
            Thread thread = new Thread(serverListener);
            thread.start();

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String input;
            while((input = console.readLine()) != null){
                out.println(userName +": "+ input );
            }
            out.close();
            console.close();
            socket.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static class ServerListener implements Runnable{
        private final Socket socket;

        public ServerListener(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try{
                BufferedReader in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String input;

                while((input = in.readLine()) != null){
                    System.out.println(input);
                }
                in.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
