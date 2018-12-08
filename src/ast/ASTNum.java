package ast;

import compiler.StackCoordinates;
import environment.Environment;
import itypes.IType;
import itypes.IntType;
import ivalues.IValue;
import ivalues.Int;

public class ASTNum implements ASTNode {
	
	private int value;
	
	public ASTNum(int value) {
		this.value = value;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		return new Int(this.value);
	}

	@Override
	public IType typecheck(Environment<IType> env) {
		return IntType.getInstance();
	}

	@Override
	public String compile(Environment<StackCoordinates> env) {
		
		String s = Integer.toString(value, 10);
		
		String code = String.format("%s%s\n", 
				"sipush ", s
				);

		return code;
	}
}