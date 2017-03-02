package Scope;

/**
 * Created by marco on 15/12/2016.
 */
public class GlobalScope extends BaseScope {

    public GlobalScope(Scope enclosingScope) {
        super(enclosingScope);
    }

    @Override
    public String getScopeName() {
        return "Global Scoope";
    }
}
