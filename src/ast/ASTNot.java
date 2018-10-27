package ast;

import ivalues.Bool;
import ivalues.IValue;
import environment.Environment;

public class ASTNot implements ASTNode {
	
	private ASTNode node;
	
	public ASTNot(ASTNode t) {
		this.node = t;
	}

	@Override
	public IValue eval(Environment env) {
		IValue v1 = node.eval(env);
		
		if( v1 instanceof Bool )
			return new Bool(!((Bool)v1).getValue());
		
		throw new RuntimeException("TypeError: Invalid ivalues to operator ~");
	}

}
