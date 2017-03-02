package Symbol;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by marco on 21/12/2016.
 */
public class SymbolTable {

    private Map<String, Symbol> symbolTable;

    public SymbolTable() {

        symbolTable = new LinkedHashMap<String, Symbol>();
    }

    public void add(Symbol symbol) {

        symbolTable.put(symbol.getName(), symbol);
    }

    public Symbol get(String name) {

        Symbol symbol = symbolTable.get(name);
        return symbol;
    }
}
