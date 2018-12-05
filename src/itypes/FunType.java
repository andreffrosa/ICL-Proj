package itypes;

import java.util.List;

public class FunType implements IType {

	private List<IType> argTypes;
	private IType returnType;

	public FunType(List<IType> argTypes, IType returnType) {
		this.argTypes = argTypes;
		this.returnType = returnType;
	}

	public List<IType> getArgTypes() {
		return this.argTypes;
	}

	public IType getReturnType() {
		return this.returnType;
	}

	public String toString() {
		// TODO StringBuild with all args and retType
		return "FUN" + this.returnType;
	}
}
