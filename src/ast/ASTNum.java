package ast;

import common.Environment;
import types.IValue;
import types.Int;

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