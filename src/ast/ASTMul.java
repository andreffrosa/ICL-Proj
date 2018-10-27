package ast;

import environment.Environment;
import ivalues.IValue;
import ivalues.Int;

public class ASTMul implements ASTNode {
	
	private ASTNode left;
	private ASTNode right;
	
	public ASTMul(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environment env) {
		return Int.multiplication((Int)left.eval(env), (Int)right.eval(env));
	}
}