package ast;

import environment.Environment;
import ivalues.Cell;
import ivalues.IValue;

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
