package AST;

import Environment.Environment;
import IValues.IValue;
import IValues.Int;

public class ASTPlus implements ASTNode {
	
	ASTNode t1,t2;
	
	public ASTPlus(ASTNode t1, ASTNode t2) {
		this.t1 = t1;
		this.t2 = t2;
	}

	@Override
	public IValue eval(Environment e) {
		
		IValue v1, v2;
		v1 = t1.eval(e);
		v2 = t2.eval(e);
		
		if( v1 instanceof Int && v2 instanceof Int ) {
			return new Int(((Int)v1).getValue() + ((Int)v2).getValue());
		} else
			throw new RuntimeException("Operator could not be apllied!");
	}
	
}
