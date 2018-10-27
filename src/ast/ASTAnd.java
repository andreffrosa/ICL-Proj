package ast;

import environment.Environment;

import ivalues.Bool;
import ivalues.IValue;

public class ASTAnd implements ASTNode {
	
	private ASTNode left;
	private ASTNode right;

	public ASTAnd(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

    @Override
	public IValue eval(Environment<IValue> env) {

    	IValue v1 = this.left.eval(env);
    	IValue v2 = this.right.eval(env);
    	
    	if( v1 instanceof Bool && v2 instanceof Bool ) {
    		return Bool.conjunction((Bool)v1, (Bool)v2);
    	}
    	throw new RuntimeException("Operator && only applicable to Bools!");
	}

}