package ast;

import ivalues.Cell;
import ivalues.IValue;
import environment.Environment;

public class ASTAssign implements ASTNode {
	
	private ASTNode left, right;
	
	public ASTAssign(ASTNode t1, ASTNode t2) {
		this.left = t1;
		this.right = t2;
	}

	@Override
	public IValue eval(Environment e) {
		IValue v1 = left.eval(e);
		IValue v2 = right.eval(e);
		
		if( v1 instanceof Cell ) {
			((Cell) v1).setValue(v2);
			return v2;
		}
			
		throw new RuntimeException("Attribuitions are only valid to Cells!");
	}
	
}

