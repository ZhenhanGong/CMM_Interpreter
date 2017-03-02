package Main;

import IO.CLI.CommandLine;
import IO.GUI.Graphics;
import Toggle.Toggle;

/**
 * Created by marco on 11/12/2016.
 */
public class Main {

    public static void main(String[] args) {

        if (Toggle.HASGUI) {

            Graphics graphics = new Graphics();
            graphics.start();

        } else {

            CommandLine commandLine = new CommandLine();
            commandLine.start();
        }
    }
}
