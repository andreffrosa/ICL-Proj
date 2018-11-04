package ast;

import ivalues.Cell;
import ivalues.IValue;

import environment.Environment;

public class ASTDerref implements ASTNode {
	
	private ASTNode node;
	
	public ASTDerref(ASTNode t) {
		this.node = t;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		IValue reference = node.eval(env);

		if( reference instanceof Cell )
			return ((Cell) reference).getValue();

		System.out.println(reference);
		System.out.println(reference.getClass().getName());
		
		throw new RuntimeException("TypeError: Value cannot be dereferenced!");
	}
	
}

