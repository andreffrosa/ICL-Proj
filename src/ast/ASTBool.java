package ast;

import IValues.Bool;
import IValues.IValue;
import environment.Environment;

public class ASTBool implements ASTNode {
	
	private boolean t;
	
	public ASTBool(boolean t) {
		this.t = t;
	}

	@Override
	public IValue eval(EnvironmentClass e) {
		return new Bool(t);
	}

}
