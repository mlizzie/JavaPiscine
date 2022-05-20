package edu.school21.sockets.server;

import edu.school21.sockets.services.UsersService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class Client extends Thread {

    private PrintWriter out;
    private BufferedReader in;
    private UsersService usersService;
    private List<Client> clients;
    private boolean stop = false;
    private String name;

    public Client(Socket clientSocket, UsersService usersService, List<Client> clients) throws IOException {

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.usersService = usersService;
        this.clients = clients;
    }


    public void send(String text){
        out.println(text);
    }

    @Override
    public void run() {

        out.println("Hello from Server!");
        String command = null;
        try {
            command = in.readLine();
            while (!command.equalsIgnoreCase("signUp") && !command.equalsIgnoreCase("signIn") ) {
                out.printf("unrecognised command '%s'\n", command);
                command = in.readLine();
                if (command.equalsIgnoreCase("signUp")){
                    this.signUp();
                }else {
                    this.signIn();
                }
                if (!stop) this.messaging();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void messaging() throws IOException {
        send("Start messaging");
        while (true){
            String line = in.readLine();
            for (Client client: clients) {
                if (this != client) {
                    client.send(name + ": -> " + line);
                }
            }

        }
    }

    public void signIn() throws IOException {

        out.println("Enter username:");
        String username = in.readLine();
        out.println("Enter password:");
        String password = in.readLine();

       if(usersService.signIn(username, password)){
           this.name = username;
       }else {
           stop = true;
       }
    }

    public void signUp() throws IOException {

        out.println("Enter username:");
        String username = in.readLine();
        out.println("Enter password:");
        String password = in.readLine();
        try{
            usersService.signUp(username,password);
            this.name = username;
            out.println("Successful!");
        }
        catch(Exception e){
            e.printStackTrace();
            out.println("error!");
            stop = true;
        }
    }

}
