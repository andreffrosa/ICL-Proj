package ast;

import environment.EnvironmentClass;
import types.IValue;
import types.Int;

public class ASTSub implements ASTNode {
	
	private ASTNode left;
	private ASTNode right;
	
	public ASTSub(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(EnvironmentClass env) {
		return Int.subtraction((Int)left.eval(env), (Int)right.eval(env));
	}
}