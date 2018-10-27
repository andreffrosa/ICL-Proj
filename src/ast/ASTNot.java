package ast;

import ivalues.Bool;
import ivalues.IValue;
import environment.Environment;

public class ASTNot implements ASTNode {
	
	private ASTNode node;
	
	public ASTNot(ASTNode node) {
		this.node = node;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		IValue v = node.eval(env);
		
		if( v instanceof Bool )
			return new Bool(!((Bool)v).getValue());
		
		throw new RuntimeException("TypeError: Invalid ivalues to operator ~");
	}

}
