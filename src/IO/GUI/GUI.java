package IO.GUI;

import IO.IO;

import javax.swing.*;

/**
 * Created by marco on 21/12/2016.
 */
public class GUI implements IO {

    private JTextArea outputArea;

    public GUI(JTextArea outputArea) {
        this.outputArea = outputArea;
    }

    @Override
    public String input() {

        return JOptionPane.showInputDialog(null, "Please Input Here!");
    }

    @Override
    public void output(Object out) {

        outputArea.append(out.toString()+"\n");
    }
}
