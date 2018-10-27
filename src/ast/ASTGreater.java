package ast;

import ivalues.Bool;
import ivalues.IValue;
import ivalues.Int;
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
		
		if( v1 instanceof Int && v2 instanceof Int ) {
			return new Bool(((Int)v1).getValue() > ((Int)v2).getValue());
		} else
			throw new RuntimeException("TypeError: Invalid ivalues to operator >");
	}
	
}
