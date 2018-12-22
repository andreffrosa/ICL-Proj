package ast;

import environment.Environment;
import itypes.DoubleType;
import itypes.IType;
import ivalues.IDouble;
import ivalues.IValue;

public class ASTDouble extends ASTNodeClass {
	
	private double value;
	
	public ASTDouble(double value) {
		this.value = value;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		return new IDouble(this.value);
	}

	@Override
	public IType typecheck(Environment<IType> env) {
		return (super.nodeType = DoubleType.getInstance());
	}

	@Override
	public String compile(Environment<String> env) {
		String s = Double.toString(value);

		return String.format("%s\n%s\n%s%s\n%s\n",
				"new java/lang/Double",
				"dup",
				"ldc2_w ", s,
				"invokespecial java/lang/Double/<init>(D)V"
				
		);
	}
}