package ast;

import ivalues.Cell;
import ivalues.IValue;
import ivalues.Int;
import environment.Environment;

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
