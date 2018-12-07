package ast;

import environment.Environment;
import itypes.IType;
import itypes.IntType;
import itypes.TypeException;
import ivalues.IValue;
import ivalues.Int;

public class ASTMult implements ASTNode {
	
	private ASTNode left;
	private ASTNode right;
	
	public ASTMult(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environment<IValue> env) {

        IValue leftVal = this.left.eval(env);
        IValue rightVal = this.right.eval(env);
        
		return Int.multiplication((Int)leftVal, (Int)rightVal);
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t1 = this.left.typecheck(env);
		IType t2 = this.right.typecheck(env);

		if(t1 instanceof IntType && t2 instanceof IntType)
			return IntType.getInstance();
		else
			throw new TypeException("*", IntType.getInstance(), IntType.getInstance(), t1, t2);
	}
}