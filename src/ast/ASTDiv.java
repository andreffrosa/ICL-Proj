package ast;

import environment.EnvironmentClass;
import types.IValue;
import types.Int;

public class ASTDiv implements ASTNode {
	
	private ASTNode left;
	private ASTNode right;
	
	public ASTDiv(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(EnvironmentClass env) {
		return Int.division((Int)left.eval(env), (Int)right.eval(env));
	}
}