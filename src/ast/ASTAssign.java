package ast;

import ivalues.Cell;
import ivalues.IValue;
import environment.Environment;

public class ASTAssign implements ASTNode {
	
	private ASTNode left, right;
	
	public ASTAssign(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		IValue v1 = left.eval(env);
		IValue v2 = right.eval(env);
		
		if( v1 instanceof Cell ) {
			((Cell) v1).setValue(v2);
			return v2;
		}
			
		throw new RuntimeException("TypeError: Attributions are only valid to Cells!");
	}
	
}

