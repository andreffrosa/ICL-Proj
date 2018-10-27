package AST;

import Environment.Environment;
import IValues.IValue;
import IValues.Int;

public class ASTNeg implements ASTNode {
	
	ASTNode t;
	
	public ASTNeg(ASTNode t) {
		this.t = t;
	}

	@Override
	public IValue eval(Environment e) {
		
		IValue v = t.eval(e);
		
		if( t instanceof Int ) {
			return new Int((-1)*((Int)v).getValue());
		} else
			throw new RuntimeException("Operator could not be apllied!");
	}
	
}
