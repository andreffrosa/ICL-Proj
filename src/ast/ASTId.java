package ast;

import environment.Environment;
import environment.FrameEnvironment;
import itypes.IType;
import ivalues.IValue;

public class ASTId extends ASTNodeClass {
	
	private String name;
	
	public ASTId(String name) {
		this.name = name;
	}
	
	public IValue eval(Environment<IValue> env) {
		IValue value = env.find(this.name);

		if(value == null) {
			throw new RuntimeException("Illegal definition of id: " + this.name);
		}

		return value;
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType type = env.find(this.name);

		if(type == null)
			throw new RuntimeException("Illegal definition of id: " + this.name);

		return (super.nodeType = type);
	}

    @Override
    public String compile(FrameEnvironment env) {

		String instructions = env.find(this.name);

		if(instructions == null)
			throw new RuntimeException("Illegal definition of id: " + this.name);

        return instructions;
    }
}
