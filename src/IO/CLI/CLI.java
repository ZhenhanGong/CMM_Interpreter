package IO.CLI;

import IO.IO;

import java.util.Scanner;

/**
 * Created by marco on 11/12/2016.
 */
public class CLI implements IO {

    @Override
    public String input() {

        System.out.println("Input a number ...");
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNext())
            return scanner.nextLine();
        return null;
    }

    @Override
    public void output(Object out) {

        System.out.println(out);
    }
}
