package IO.CLI;

import Compilation.Compiler;
import TestScripts.TestScripts;
import TestScripts.MyTestScripts;

/**
 * Created by marco on 21/12/2016.
 */
public class CommandLine {

    CLI io;
    String testScript = TestScripts.test1;
    String myTestScript = MyTestScripts.test1;

    public CommandLine() {

        io = new CLI();
    }

    public void start() {

        Compiler compiler = new Compiler(testScript, io, io);
        compiler.run();
    }
}
