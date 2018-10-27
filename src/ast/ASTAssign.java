package ast;

import types.Cell;
import types.IValue;
import environment.Environment;

public class ASTAssign implements ASTNode {
	
	private ASTNode t1, t2;
	
	public ASTAssign(ASTNode t1, ASTNode t2) {
		this.t1 = t1;
		this.t2 = t2;
	}

	@Override
	public IValue eval(Environment e) {
		IValue v1 = t1.eval(e);
		IValue v2 = t2.eval(e);
		
		if( v1 instanceof Cell ) {
			((Cell) v1).setValue(v2);
			return v2;
		}
			
		throw new RuntimeException("Attribuitions are only valid to Cells!");
	}
	
}

