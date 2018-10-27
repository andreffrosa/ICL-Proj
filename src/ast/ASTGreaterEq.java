package ast;

import ivalues.Bool;
import ivalues.IValue;
import ivalues.Int;
import environment.Environment;

public class ASTGreaterEq implements ASTNode {
	
	ASTNode left, right;
	
	public ASTGreaterEq(ASTNode t1, ASTNode t2) {
		this.left = t1;
		this.right = t2;
	}

	@Override
	public IValue eval(Environment e) {
		
		IValue v1 = left.eval(e);
		IValue v2 = right.eval(e);
		
		if( v1 instanceof Int && v2 instanceof Int ) {
			return new Bool(((Int)v1).getValue() >= ((Int)v2).getValue());
		} else
			throw new RuntimeException("Operator >= could not be applied.!");
	}
	
}
