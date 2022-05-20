package edu.school21.sockets.server;

import edu.school21.sockets.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class Server {

    private UsersService usersService;
    private int port;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    @Autowired
    public Server(UsersService usersService) {
        this.usersService = usersService;
    }


    public  void start(int port) throws IOException {

        this.port = port;
        System.out.println("Server  started.");
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        System.out.println("Connection accepted.");

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        out.println("Hello from Server!");
        String command = in.readLine();
        while (!command.equalsIgnoreCase("signUp")) {
            out.printf("unrecognised command '%s'\n", command);
            command = in.readLine();
        }

        out.println("Enter username:");
        String username = in.readLine();
        out.println("Enter password:");
        String password = in.readLine();
        try{
            usersService.signUp(username,password);
            out.println("Successful!");
        }
        catch(Exception e){
            e.printStackTrace();
            out.println("error!");
        }
        this.stop();
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}
