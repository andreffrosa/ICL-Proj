package ast;

import IValues.Cell;
import IValues.IValue;
import IValues.Int;
import environment.Environment;

public class ASTNew implements ASTNode {
	
	private ASTNode t;
	
	public ASTNew(ASTNode t) {
		this.t = t;
	}

	@Override
	public IValue eval(EnvironmentClass e) {
		IValue v = t.eval(e);
		return new Cell(v);
	}
	
}
