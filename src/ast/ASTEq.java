package ast;

import ivalues.Bool;
import ivalues.IValue;
import ivalues.Int;
import environment.Environment;

public class ASTEq implements ASTNode {
	
	private ASTNode left, right;
	
	public ASTEq(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		IValue v1 = left.eval(env);
		IValue v2 = right.eval(env);
		
		if( v1 instanceof Int && v2 instanceof Int )
			return new Bool(((Int)v1).getValue() == ((Int)v2).getValue());
		else if( v1 instanceof Bool && v2 instanceof Bool)
			return new Bool(((Bool)v1).getValue() == ((Bool)v2).getValue());
		
		throw new RuntimeException("TypeError: Invalid ivalues to operator ==");
	}

}
