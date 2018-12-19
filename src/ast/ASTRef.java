package ast;

import environment.Environment;
import environment.FrameEnvironment;
import itypes.IType;
import itypes.RefType;
import ivalues.Cell;
import ivalues.IValue;

public class ASTRef extends ASTNodeClass {

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
        return (super.nodeType = new RefType(this.value.typecheck(env)));
    }

    @Override
    public String compile(FrameEnvironment env) {
        return null;
    }
}
