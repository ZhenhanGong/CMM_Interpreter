package Expression;

import Type.Type;

/**
 * Created by marco on 15/12/2016.
 */
public class ExprValue {

    private Type type;
    private Object value;

    public ExprValue() {
    }

    public ExprValue(Type type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
