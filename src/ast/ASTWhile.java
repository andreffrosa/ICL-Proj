package AST;

import Environment.Environment;
import IValues.Bool;
import IValues.IValue;

public class ASTWhile implements ASTNode {
	
	private ASTNode condition, body;
	
	public ASTWhile(ASTNode condition, ASTNode body) {
		this.condition = condition;
		this.body = body;
	}

	@Override
	public IValue eval(Environment e) { // O que retorna a avaliação do while?
		
		IValue result = null;
		
		while(true) {
			IValue cond = condition.eval(e);
			
			if( cond instanceof Bool) {
				if( ((Bool) cond).getValue() ) {
					result = body.eval(e);
					System.out.println(result);
				} else {
					return result;
				}
			} else
				throw new RuntimeException("Condition does not evaluate to a Bool!");
		}
	}
}
