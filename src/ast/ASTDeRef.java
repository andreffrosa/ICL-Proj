package ast;

import environment.EnvironmentClass;
import types.Cell;
import types.IValue;

public class ASTDeRef implements ASTNode {

    private ASTNode ref;

    public ASTDeRef(ASTNode ref) {
        this.ref = ref;
    }

    @Override
    public IValue eval(EnvironmentClass env) {
        return ((Cell) this.ref.eval(env)).getValue();
    }
}
