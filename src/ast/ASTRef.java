package ast;

import environment.EnvironmentClass;
import types.Cell;
import types.IValue;

public class ASTRef implements ASTNode {

    private ASTNode value;

    public ASTRef(ASTNode value) {
        this.value = value;
    }

    @Override
    public IValue eval(EnvironmentClass env) {
        return new Cell(this.value.eval(env));
    }
}
