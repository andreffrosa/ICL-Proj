package ast;

import compiler.StackCoordinates;
import environment.Environment;
import itypes.BoolType;
import itypes.IType;
import itypes.TypeException;
import ivalues.Bool;
import ivalues.IValue;

public class ASTOr implements ASTNode {
	
	private ASTNode left;
	private ASTNode right;
	
	public ASTOr(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
	    IValue leftVal = this.left.eval(env);
	    IValue rightVal = this.right.eval(env);

		return Bool.disjunction((Bool) leftVal, (Bool) rightVal);
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t1 = this.left.typecheck(env);
		IType t2 = this.right.typecheck(env);

		if(t1 instanceof BoolType && t2 instanceof BoolType)
			return BoolType.getInstance();
		else
			throw new TypeException("||", BoolType.getInstance(), BoolType.getInstance(), t1, t2);
	}

	@Override
	public String compile(Environment<StackCoordinates> env) {
		// TODO Auto-generated method stub
		return null;
	}
}