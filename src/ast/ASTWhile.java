package ast;

import ivalues.Bool;
import ivalues.IValue;
import environment.Environment;

public class ASTWhile implements ASTNode {
	
	private ASTNode condition, body;
	
	public ASTWhile(ASTNode condition, ASTNode body) {
		this.condition = condition;
		this.body = body;
	}

	@Override
	public IValue eval(Environment<IValue> e) {
		
		while(true) {
			IValue cond = condition.eval(e);
			
			if( cond instanceof Bool) {
				if( ((Bool) cond).getValue() ) {
					IValue result = body.eval(e);
					// System.out.println(result);
				} else {
					return cond;
				}
			} else
				throw new RuntimeException("TypeError: Condition does not evaluate to a Bool!");
		}
	}
}
