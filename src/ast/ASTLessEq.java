package ast;

import ivalues.Bool;
import ivalues.IValue;
import ivalues.Int;
import environment.Environment;

public class ASTLessEq implements ASTNode {
	
	ASTNode left, right;
	
	public ASTLessEq(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		
		IValue v1 = left.eval(env);
		IValue v2 = right.eval(env);
		
		if( v1 instanceof Int && v2 instanceof Int ) {
			return new Bool(((Int)v1).getValue() <= ((Int)v2).getValue());
		} else
			throw new RuntimeException("TypeError: Invalid ivalues to operator <=");
	}
	
}
