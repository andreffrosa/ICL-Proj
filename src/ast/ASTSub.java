package ast;

import ast.ASTNode;
import environment.Environment;
import ivalues.IValue;
import ivalues.Int;

public class ASTSub implements ASTNode {
	
	private ASTNode left;
	private ASTNode right;
	
	public ASTSub(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environment<IValue> e) {
		
		IValue v1, v2;
		v1 = left.eval(e);
		v2 = right.eval(e);
		
		if( v1 instanceof Int && v2 instanceof Int ) {
			return new Int(((Int)v1).getValue() - ((Int)v2).getValue());
		} else
			throw new RuntimeException("TypeError: Invalid ivalues to operator -");
	}
}