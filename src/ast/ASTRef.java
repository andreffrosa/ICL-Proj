package ast;

import compiler.StackCoordinates;
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

	@Override
	public String compile(Environment<StackCoordinates> env) {
		// TODO Auto-generated method stub
		return null;
	}
}
