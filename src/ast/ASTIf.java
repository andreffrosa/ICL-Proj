package AST;

import Environment.Environment;
import IValues.Bool;
import IValues.IValue;

public class ASTIf implements ASTNode {
	
	private ASTNode condition, t1, t2;
	
	public ASTIf(ASTNode condition, ASTNode t1, ASTNode t2) {
		this.condition = condition;
		this.t1 = t1;
		this.t2 = t2;
	}

	@Override
	public IValue eval(Environment e) {
		IValue cond = condition.eval(e);
		if( cond instanceof Bool ) {
			
			if( ((Bool) cond).getValue() ) {
				return t1.eval(e);
			} else {
				return t2.eval(e);
			}
		}
		throw new RuntimeException("Condition does not evaluate to a boolean!");
	}

}
