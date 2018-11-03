package ast;

import java.util.Map;
import java.util.Map.Entry;

import ivalues.Bool;
import ivalues.IValue;
import environment.Environment;

public class ASTFor implements ASTNode {
	
	private Map<String, ASTNode> declarations;
	private ASTNode condition, step, body;
	
	public ASTFor(Map<String, ASTNode> decls, ASTNode condition, ASTNode step, ASTNode body) {
		this.declarations = decls;
		this.condition = condition;
		this.body = body;
		this.step = step;
	}

	@Override
	public IValue eval(Environment<IValue> e) { // O que retorna a avaliação do for?
		
		Environment<IValue> e2 = e.beginScope();
		
		for( Entry<String, ASTNode> dec : declarations.entrySet() ) {
			String id = dec.getKey();
			IValue val = dec.getValue().eval(e2);
			e2.associate(id, val);
		}
		
		while(true) {
			IValue cond = condition.eval(e2);
			
			if( cond instanceof Bool) {
				if( ((Bool) cond).getValue() ) {
					IValue result = body.eval(e2);
					System.out.println(result);
					step.eval(e2);
				} else {
					return cond;
				}
			} else
				throw new RuntimeException("TypeError: Condition does not evaluate to a Bool!");
		}
	}
}
