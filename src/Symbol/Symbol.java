package Symbol;

import Type.Type;
import Scope.Scope;

/**
 * Created by marco on 14/12/2016.
 */
public class Symbol {

    private String name;
    private Object value;
    private Type type;
    private Scope scope;

    public Symbol(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public Symbol(String name, Type type, Object value) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }

    public void setValue(Object value) {

        this.value = value;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }
}
