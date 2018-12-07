package ast;

import itypes.IType;
import itypes.IntType;
import itypes.TypeException;
import ivalues.IValue;
import ivalues.Int;
import environment.Environment;

public class ASTPlus implements ASTNode {
	
	ASTNode left, right;
	
	public ASTPlus(ASTNode t1, ASTNode t2) {
		this.left = t1;
		this.right = t2;
	}

	@Override
	public IValue eval(Environment<IValue> e) {
		
		IValue v1 = left.eval(e);
		IValue v2 = right.eval(e);
		
		return new Int(((Int)v1).getValue() + ((Int)v2).getValue());
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t1 = this.left.typecheck(env);
		IType t2 = this.right.typecheck(env);

		if(t1 instanceof IntType && t2 instanceof IntType)
			return IntType.getInstance();
		else
			throw new TypeException("+", IntType.getInstance(), IntType.getInstance(), t1, t2);
	}

}
