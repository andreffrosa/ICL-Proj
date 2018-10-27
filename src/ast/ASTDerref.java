package ast;

import ivalues.Cell;
import ivalues.IValue;

import environment.Environment;

public class ASTDerref implements ASTNode {
	
	private ASTNode t;
	
	public ASTDerref(ASTNode t) {
		this.t = t;
	}

	@Override
	public IValue eval(Environment e) {
		IValue v = t.eval(e);
		
		if( v instanceof Cell )
			return ((Cell) v).getValue();
		
		throw new RuntimeException("Value cannot be derreferenciated!");
	}
	
}

