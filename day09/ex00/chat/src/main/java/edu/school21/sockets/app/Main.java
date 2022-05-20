package edu.school21.sockets.app;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.server.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Main {
    @Parameter(names = "--port")
     static Integer port;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        Main main = new Main();
        JCommander.newBuilder().addObject(main).build().parse(args);
        Server server = context.getBean(Server.class);
        try{
            server.start(port);
        }
        catch (Exception e){
            System.err.println(e.getClass() +": " + e.getMessage());
        }
    }
}
