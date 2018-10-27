package AST;

import Environment.Environment;
import IValues.Cell;
import IValues.IValue;
import IValues.Int;

public class ASTNew implements ASTNode {
	
	private ASTNode t;
	
	public ASTNew(ASTNode t) {
		this.t = t;
	}

	@Override
	public IValue eval(Environment e) {
		IValue v = t.eval(e);
		return new Cell(v);
	}
	
}
