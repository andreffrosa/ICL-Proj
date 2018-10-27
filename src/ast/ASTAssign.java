package ast;

import common.Environment;
import types.Cell;
import types.IValue;

public class ASTAssign implements ASTNode {

    private ASTNode ref;
    private ASTNode value;

    public ASTAssign(ASTNode ref, ASTNode value) {
        this.ref = ref;
        this.value = value;
    }

    @Override
    public IValue eval(Environment env) {
        return ((Cell)this.ref.eval(env)).setValue(this.value.eval(env));
    }
}
