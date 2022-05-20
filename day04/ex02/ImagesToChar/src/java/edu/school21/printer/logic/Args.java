package edu.school21.printer.logic;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.io.IOException;

@Parameters(separators = "=")

public class Args {

    @Parameter(names = {"--white"})
    private static String arg1;
    @Parameter(names = {"--black"})
    private static String arg2;

    public void run() throws IOException {
        Logic.convert(arg1,arg2);
    }
}
