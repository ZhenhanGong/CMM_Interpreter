package IO.GUI;

import Compilation.Compiler;

import javax.swing.*;
import java.awt.*;

/**
 * Created by marco on 21/12/2016.
 */
public class Graphics extends JFrame {

    String testScript;
    GUI lexicalResultIO;
    GUI consoleIO;

    public Graphics() throws HeadlessException {

        //TODO

        //testScript = script;
        //lexicalResultIO = new GUI();
        //consoleIO = new GUI();
    }


    public void start() {

        Compiler compiler = new Compiler(testScript, lexicalResultIO, consoleIO);
        compiler.run();
    }
}
