package ast;

import ivalues.Bool;
import ivalues.IValue;
import environment.Environment;

public class ASTIf implements ASTNode {
	
	private ASTNode condition, left, right;
	
	public ASTIf(ASTNode condition, ASTNode left, ASTNode right) {
		this.condition = condition;
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environment env) {
		IValue cond = condition.eval(env);
		if( cond instanceof Bool ) {
			
			if( ((Bool) cond).getValue() ) {
				return left.eval(env);
			} else {
				return right.eval(env);
			}
		}
		throw new RuntimeException("Condition does not evaluate to a boolean!");
	}

}
