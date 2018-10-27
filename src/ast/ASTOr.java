package ast;

import environment.EnvironmentClass;
import types.Bool;
import types.IValue;

public class ASTOr implements ASTNode {
	
	private ASTNode left;
	private ASTNode right;
	
	public ASTOr(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(EnvironmentClass env) {
		return Bool.disjunction((Bool) this.left.eval(env), (Bool) this.right.eval(env));
	}
}