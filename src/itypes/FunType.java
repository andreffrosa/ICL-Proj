package itypes;

import environment.Environment;

import java.util.List;

public class FunType implements IType {

	private List<IType> argTypes;
	private IType returnType;
	private Environment<IType> definitionEnv;

	public FunType(List<IType> argTypes, IType returnType, Environment<IType> definitionEnv) {
		this.argTypes = argTypes;
		this.returnType = returnType;
		this.definitionEnv = definitionEnv;
	}

	public List<IType> getArgTypes() {
		return this.argTypes;
	}

	public IType getReturnType() {
		return this.returnType;
	}

	public Environment<IType> getDefinitionEnv() {
		return this.definitionEnv;
	}

	public String toString() {
		// TODO StringBuild with all args and retType
		return "FUN" + this.returnType;
	}
}
