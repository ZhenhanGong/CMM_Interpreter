package Scope;

import Symbol.Symbol;
import Symbol.SymbolTable;

/**
 * Created by marco on 15/12/2016.
 */
public abstract class BaseScope implements Scope {

    private Scope enclosingScope;
    private SymbolTable symbolTable;

    public BaseScope(Scope enclosingScope) {

        this.enclosingScope = enclosingScope;
        symbolTable = new SymbolTable();
    }

    @Override
    public Scope getEnclosingScope() {

        return enclosingScope;
    }

    @Override
    public void declare(Symbol symbol) {

        symbolTable.add(symbol);
        symbol.setScope(this);
    }

    @Override
    public Boolean isRedundant(String name) {

        return symbolTable.get(name) != null;
    }

    @Override
    public Symbol resolve(String name) {

        Symbol symbol = symbolTable.get(name);
        if (symbol != null)
            return symbol;
        if (enclosingScope != null)
            return enclosingScope.resolve(name);
        return null;
    }

    @Override
    public String toString() {

        return getScopeName() + ":" + symbolTable.toString();
    }
}
