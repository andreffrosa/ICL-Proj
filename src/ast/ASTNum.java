package ast;

import environment.Environment;
import environment.FrameEnvironment;
import itypes.IType;
import itypes.IntType;
import ivalues.IValue;
import ivalues.Int;

public class ASTNum extends ASTNodeClass {
	
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
		return (super.nodeType = IntType.getInstance());
	}

	@Override
	public String compile(FrameEnvironment env) {
		String s = Integer.toString(value, 10);

		return String.format("%s%s\n",
				"sipush ", s
		);
	}
}