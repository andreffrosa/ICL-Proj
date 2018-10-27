package ast;

import ivalues.IValue;
import ivalues.Int;
import environment.Environment;

public class ASTNeg implements ASTNode {
	
	ASTNode node;
	
	public ASTNeg(ASTNode node) {
		this.node = node;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		
		IValue v = node.eval(env);
		
		if( node instanceof Int ) {
			return new Int((-1)*((Int)v).getValue());
		} else
			throw new RuntimeException("Operator - could not be applied.!");
	}
	
}
