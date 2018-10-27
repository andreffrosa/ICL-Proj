package ast;

import IValues.Bool;
import IValues.IValue;
import IValues.Int;
import environment.Environment;

public class ASTEq implements ASTNode {
	
	private ASTNode t1, t2;
	
	public ASTEq(ASTNode t1, ASTNode t2) {
		this.t1 = t1;
		this.t2 = t2;
	}

	@Override
	public IValue eval(EnvironmentClass e) {
		IValue v1 = t1.eval(e);
		IValue v2 = t2.eval(e);
		
		if( v1 instanceof Int && v2 instanceof Int )
			return new Bool(((Int)v1).getValue() == ((Int)v2).getValue());
		else if( v1 instanceof Bool && v2 instanceof Bool)
			return new Bool(((Bool)v1).getValue() == ((Bool)v2).getValue());
		
		throw new RuntimeException("TypeError: Invalid types to ==");
	}

}
