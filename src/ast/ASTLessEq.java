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
	public IValue eval(Environment<IValue> e) {
		
		IValue v1 = left.eval(e);
		IValue v2 = right.eval(e);
		
		if( v1 instanceof Int && v2 instanceof Int ) {
			return new Bool(((Int)v1).getValue() <= ((Int)v2).getValue());
		} else
			throw new RuntimeException("Operator <= could not be apllied!");
	}
	
}
