package ast;

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
	public IValue eval(Environment env) {
		return Int.subtraction((Int)left.eval(env), (Int)right.eval(env));
	}
}