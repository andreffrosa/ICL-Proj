package ast;

import environment.Environment;

import ivalues.IValue;
import ivalues.Int;

public class ASTDiv implements ASTNode {
	
	private ASTNode left;
	private ASTNode right;
	
	public ASTDiv(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environment<IValue> env) {

		IValue v1 = left.eval(env);
		IValue v2 = right.eval(env);
		
		if( v1 instanceof Int && v2 instanceof Int ) {
			return Int.division((Int)left.eval(env), (Int)right.eval(env));
		} else
			throw new RuntimeException("Operator / cannot be applied.!");
	}
}