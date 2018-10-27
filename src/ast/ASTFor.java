package ast;

import java.util.List;
import java.util.Map.Entry;

import ivalues.Bool;
import ivalues.IValue;
import environment.Environment;

public class ASTFor implements ASTNode {
	
	private List<Entry<String, ASTNode>> decls;
	private ASTNode condition, step, body;
	
	public ASTFor(List<Entry<String, ASTNode>> decls, ASTNode condition, ASTNode step, ASTNode body) {
		this.decls = decls;
		this.condition = condition;
		this.body = body;
		this.step = step;
	}

	@Override
	public IValue eval(Environment e) { // O que retorna a avaliação do while?
		
		Environment e2 = e.beginScope();
		
		for( Entry<String, ASTNode> dec : decls ) {
			String id = dec.getKey();
			IValue val = dec.getValue().eval(e2);
			e2.associate(id, val);
		}
		
		IValue result = null;
		
		while(true) {
			IValue cond = condition.eval(e2);
			
			if( cond instanceof Bool) {
				if( ((Bool) cond).getValue() ) {
					result = body.eval(e2);
					System.out.println(result);
					step.eval(e2);
				} else {
					return result;
				}
			} else
				throw new RuntimeException("Condition does not evaluate to a Bool!");
		}
	}
}
