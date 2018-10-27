package ast;

import ivalues.Bool;
import ivalues.IValue;
import environment.Environment;

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
