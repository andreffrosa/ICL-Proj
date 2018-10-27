package ast;

import common.Environment;
import types.IValue;
import types.Int;

public class ASTEq implements ASTNode {
	
	private ASTNode left;
	private ASTNode right;
	
	public ASTEq(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

    @Override
	public IValue eval(Environment env) {
		return Int.equal((Int) this.left.eval(env), (Int) this.right.eval(env));
	}
}