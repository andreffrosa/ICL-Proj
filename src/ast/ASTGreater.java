package ast;

import itypes.BoolType;
import itypes.IType;
import itypes.IntType;
import itypes.TypeException;
import ivalues.Bool;
import ivalues.IValue;
import ivalues.Int;
import compiler.StackCoordinates;
import environment.Environment;

public class ASTGreater implements ASTNode {
	
	private ASTNode left, right;
	
	public ASTGreater(ASTNode t1, ASTNode t2) {
		this.left = t1;
		this.right = t2;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		
		IValue v1 = left.eval(env);
		IValue v2 = right.eval(env);
		
		return new Bool(((Int)v1).getValue() > ((Int)v2).getValue());
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t1 = this.left.typecheck(env);
		IType t2 = this.right.typecheck(env);

		if(t1 instanceof IntType && t2 instanceof IntType)
			return BoolType.getInstance();
		else
			throw new TypeException(">", IntType.getInstance(), IntType.getInstance(), t1, t2);
	}

	@Override
	public String compile(Environment<StackCoordinates> env) {
		// TODO Auto-generated method stub
		return null;
	}

}
