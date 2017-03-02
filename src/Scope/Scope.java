package Scope;

import Symbol.Symbol;

/**
 * Created by marco on 14/12/2016.
 */
public interface Scope {

    public String getScopeName();
    public Scope getEnclosingScope();
    public void declare(Symbol symbol);
    public Boolean isRedundant(String name);
    public Symbol resolve(String name);
}
