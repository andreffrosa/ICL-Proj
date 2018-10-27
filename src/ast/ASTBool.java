package AST;

import Environment.Environment;
import IValues.Bool;
import IValues.IValue;

public class ASTBool implements ASTNode {
	
	private boolean t;
	
	public ASTBool(boolean t) {
		this.t = t;
	}

	@Override
	public IValue eval(Environment e) {
		return new Bool(t);
	}

}
