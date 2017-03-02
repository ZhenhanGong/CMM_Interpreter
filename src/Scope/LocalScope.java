package Scope;

/**
 * Created by marco on 15/12/2016.
 */
public class LocalScope extends BaseScope {

    public LocalScope(Scope enclosingScope) {
        super(enclosingScope);
    }

    @Override
    public String getScopeName() {
        return "Local Scope";
    }
}
