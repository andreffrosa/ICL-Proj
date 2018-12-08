package ast;

import itypes.BoolType;
import itypes.IType;
import ivalues.Bool;
import ivalues.IValue;
import compiler.StackCoordinates;
import environment.Environment;

public class ASTBool implements ASTNode {
	
	private boolean value;
	
	public ASTBool(boolean v) {
		this.value = v;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		return new Bool(value);
	}

	@Override
	public IType typecheck(Environment<IType> env) {
		return BoolType.getInstance();
	}

	@Override
	public String compile(Environment<StackCoordinates> env) {
		// TODO Auto-generated method stub
		return null;
	}
}
