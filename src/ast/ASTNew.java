package ast;

import ivalues.Cell;
import ivalues.IValue;
import environment.Environment;

public class ASTNew implements ASTNode {
	
	private ASTNode node;
	
	public ASTNew(ASTNode t) {
		this.node = t;
	}

	@Override
	public IValue eval(Environment env) {
		IValue value = node.eval(env);
		return new Cell(value);
	}
	
}
