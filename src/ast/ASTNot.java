package ast;

import ivalues.Bool;
import ivalues.IValue;
import ivalues.Int;
import environment.Environment;

public class ASTNot implements ASTNode {
	
	private ASTNode t1;
	
	public ASTNot(ASTNode t1) {
		this.t1 = t1;
	}

	@Override
	public IValue eval(Environment e) {
		IValue v1 = t1.eval(e);
		
		if( v1 instanceof Bool )
			return new Bool(!((Bool)v1).getValue());
		
		throw new RuntimeException("TypeError: Invalid ivalues to ~");
	}

}
