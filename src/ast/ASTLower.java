package ast;

import IValues.Bool;
import IValues.IValue;
import IValues.Int;
import environment.Environment;

public class ASTLower implements ASTNode {
	
	ASTNode t1,t2;
	
	public ASTLower(ASTNode t1, ASTNode t2) {
		this.t1 = t1;
		this.t2 = t2;
	}

	@Override
	public IValue eval(EnvironmentClass e) {
		
		IValue v1, v2;
		v1 = t1.eval(e);
		v2 = t2.eval(e);
		
		if( v1 instanceof Int && v2 instanceof Int ) {
			return new Bool(((Int)v1).getValue() < ((Int)v2).getValue());
		} else
			throw new RuntimeException("Operator could not be apllied!");
	}
	
}
