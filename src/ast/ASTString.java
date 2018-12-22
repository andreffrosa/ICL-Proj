package ast;

import itypes.IType;
import itypes.StrType;
import ivalues.IValue;
import ivalues.Str;
import environment.Environment;

public class ASTString extends ASTNodeClass {
	
	private String value;
	
	public ASTString(String v) {
		this.value = v;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		return new Str(value);
	}

	@Override
	public IType typecheck(Environment<IType> env) {
		return (super.nodeType = StrType.getInstance());
	}

    @Override
    public String compile(Environment<String> env) {
		return String.format("%s\"%s\"\n",
				"ldc ", value
		);
    }
}
