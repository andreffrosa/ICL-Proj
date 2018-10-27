package ast;

import ivalues.Bool;
import ivalues.IValue;
import environment.Environment;

public class ASTIf implements ASTNode {
	
	private ASTNode condition, if_body, else_body;
	
	public ASTIf(ASTNode condition, ASTNode if_body, ASTNode else_body) {
		this.condition = condition;
		this.if_body = if_body;
		this.else_body = else_body;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		IValue cond = condition.eval(env);
		if( cond instanceof Bool ) {
			
			if( ((Bool) cond).getValue() ) {
				return if_body.eval(env);
			} else {
				return else_body.eval(env);
			}
		}
		throw new RuntimeException("TypeError: Condition does not evaluate to a boolean!");
	}

}
