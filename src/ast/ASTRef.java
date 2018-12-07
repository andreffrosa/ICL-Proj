package ast;

import environment.Environment;
import itypes.IType;
import itypes.RefType;
import ivalues.Cell;
import ivalues.IValue;

public class ASTRef implements ASTNode {

    private ASTNode value;

    public ASTRef(ASTNode value) {
        this.value = value;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        return new Cell(this.value.eval(env));
    }

    @Override
    public IType typecheck(Environment<IType> env) {
        return new RefType(this.value.typecheck(env));
    }
}
