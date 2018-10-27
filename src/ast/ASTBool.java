package ast;

import ivalues.Bool;
import ivalues.IValue;
import environment.Environment;

public class ASTBool implements ASTNode {
	
	private boolean value;
	
	public ASTBool(boolean v) {
		this.value = v;
	}

	@Override
	public IValue eval(Environment env) {
		return new Bool(value);
	}

}
