package edu.school21.sockets.server;

import edu.school21.sockets.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Component
public class Server {

    private UsersService usersService;
    private int port;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private List<Client> clients;

    @Autowired
    public Server(UsersService usersService) {
        this.usersService = usersService;
    }

    public void start(int port) throws IOException {

        this.port = port;
        System.out.println("Server  started.");
        clients = new ArrayList<Client>();

        serverSocket = new ServerSocket(port);

        while (true){
            clientSocket = serverSocket.accept();
            Client client = new Client(clientSocket, usersService,clients);
            clients.add(new Client(clientSocket, usersService,clients));
            client.start();
        }
    }

}
