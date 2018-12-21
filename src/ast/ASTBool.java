package ast;

import itypes.BoolType;
import itypes.IType;
import ivalues.Bool;
import ivalues.IValue;
import environment.Environment;

public class ASTBool extends ASTNodeClass {
	
	private boolean value;
	
	public ASTBool(boolean v) {
		this.value = v;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		return new Bool(value);
	}

	@Override
	public IType typecheck(Environment<IType> env) {
		return (super.nodeType = BoolType.getInstance());
	}

    @Override
    public String compile(Environment<String> env) {
		String v = value ? "true" : "false";
		String s = Integer.toString((value ? 1 : 0), 10);

		return String.format("%s%s%s%s\n",
				"iconst_", s, " ;", v
		);
    }
}
