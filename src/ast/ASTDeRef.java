package ast;

import common.Environment;
import types.Cell;
import types.IValue;

public class ASTDeRef implements ASTNode {

    private ASTNode ref;

    public ASTDeRef(ASTNode ref) {
        this.ref = ref;
    }

    @Override
    public IValue eval(Environment env) {
        return ((Cell) this.ref.eval(env)).getValue();
    }
}
