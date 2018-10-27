package ast;

import common.Environment;
import types.IValue;
import types.Int;

public class ASTPlus implements ASTNode {
	
	private ASTNode left;
	private ASTNode right;
	
	public ASTPlus(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environment env) {
		return Int.addition((Int)left.eval(env), (Int)right.eval(env));
	}
}