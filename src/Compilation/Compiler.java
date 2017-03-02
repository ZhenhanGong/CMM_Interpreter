package Compilation;

import Toggle.Toggle;
import IO.IO;
import antlr.CmmLexer;
import antlr.CmmParser;
import Token.CmmToken;
import Token.TokenDictionary;
import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * Created by marco on 11/12/2016.
 */

import java.util.List;

public class Compiler {

    private String source;
    private IO lexerIO;
    private IO consoleIO;
    CmmLexer lexer;
    ParseTree parseTree;

    public Compiler(String source, IO lexerIO, IO consoleIO) {

        this.source = source;
        this.lexerIO = lexerIO;
        this.consoleIO = consoleIO;
    }

    public void run(){

        try {

            consoleIO.output("--------Start Compilation--------");
            lexerAnalyze();
            syntaxAnalyze();
            semanticsAnalyze();

        } catch (Exception e) {

            consoleIO.output(e.getMessage());
            if(Toggle.DEBUG)
                e.printStackTrace();
        }
    }


    private void lexerAnalyze() {

        lexer = new CmmLexer(new ANTLRInputStream(source));

        lexerIO.output("--------Lexer anaylze result--------");
        lexerIO.output("Token\t\t\t\tLine\t\t\t\tType");

        List<CmmToken> tokenList = (List<CmmToken>) lexer.getAllTokens();

        if (Toggle.SHOWLEXICALRESULT) {

            for (Token token : tokenList) {
                lexerIO.output(token.getText() + "\t\t\t\t\t" + token.getLine() + "\t\t\t\t\t"
                        + TokenDictionary.getTokenType(token.getType()));
            }
        }
        lexer.reset();
    }

    private void syntaxAnalyze() {

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        CmmParser parser = new CmmParser(tokenStream);
        parseTree = parser.program();

        if (Toggle.SHOWPARSERTREERESULT) {
            consoleIO.output("--------Show Tree--------");
            Trees.inspect(parseTree, parser);
        }
    }

    private void semanticsAnalyze() {

        ParseTreeWalker walker = new ParseTreeWalker();
        DeclareListener declareListener = new DeclareListener(consoleIO);
        walker.walk(declareListener, parseTree);

        ReferVisitor referVisitor = new ReferVisitor(consoleIO, declareListener.getScopes(),
                declareListener.getGlobalScope());
        referVisitor.visit(parseTree);
    }
}
