package ast;

import environment.Environment;
import ivalues.IValue;
import ivalues.Int;

public class ASTNum implements ASTNode {
	
	private int value;
	
	public ASTNum(int value) {
		this.value = value;
	}

	@Override
	public IValue eval(Environment env) {
		return new Int(this.value);
	}
}