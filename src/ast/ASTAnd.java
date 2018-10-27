package ast;

import common.Environment;
import types.Bool;
import types.IValue;

public class ASTAnd implements ASTNode {
	
	private ASTNode left;
	private ASTNode right;

	public ASTAnd(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

    @Override
	public IValue eval(Environment env) {
		return Bool.conjunction((Bool) this.left.eval(env), (Bool) this.right.eval(env));
	}
}