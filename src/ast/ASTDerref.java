package ast;

import IValues.Cell;
import IValues.IValue;
import IValues.Int;
import environment.Environment;

public class ASTDerref implements ASTNode {
	
	private ASTNode t;
	
	public ASTDerref(ASTNode t) {
		this.t = t;
	}

	@Override
	public IValue eval(EnvironmentClass e) {
		IValue v = t.eval(e);
		
		if( v instanceof Cell )
			return ((Cell) v).getValue();
		
		throw new RuntimeException("Value cannot be derreferenciated!");
	}
	
}

