package edu.school21.printer.app;

import com.beust.jcommander.JCommander;
import edu.school21.printer.logic.Args;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        if (args.length != 2){
            System.err.println("use: Program --white=color --black=color");
            System.exit(-1);
        }
        Args arguments = new Args();
        try {
            JCommander.newBuilder().addObject(arguments).build().parse(args);
            arguments.run();
        }
        catch (IllegalArgumentException  e){
            System.err.println("Invalid color in param");
        }
        catch (IOException e) {
            System.err.println("Exception IO");
        }
    }
}
