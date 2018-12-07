package itypes;

import java.util.Iterator;
import java.util.List;

public class FunType implements IType {

	private List<IType> paramTypes;
	private IType returnType;

	public FunType(List<IType> paramTypes, IType returnType) {
		this.paramTypes = paramTypes;
		this.returnType = returnType;
	}

	public List<IType> getParamTypes() {
		return this.paramTypes;
	}

	public IType getReturnType() {
		return this.returnType;
	}

	public String toString() {

		StringBuilder builder = new StringBuilder("FUN");
		builder.append("(");

		for(IType paramType : this.paramTypes) {
			builder.append(paramType);
			builder.append(",");
		}
		builder.deleteCharAt(builder.lastIndexOf(","));
		builder.append(")");
		builder.append(this.getReturnType());

		return builder.toString();
	}

	@Override
	public boolean equalsType(IType type) {
		if(type == null)
			return false;

		if(!(type instanceof FunType))
			return false;

		if(!(this.getReturnType().equalsType(((FunType) type).getReturnType())))
			return false;

		if(this.getParamTypes().size() != ((FunType) type).getParamTypes().size())
			return false;

		Iterator<IType> thisParamTypes = this.getParamTypes().iterator();
		Iterator<IType> otherParamTypes = ((FunType) type).getParamTypes().iterator();

		while(thisParamTypes.hasNext() && otherParamTypes.hasNext()) {
			if(!thisParamTypes.next().equalsType(otherParamTypes.next()))
				return false;
		}

		return true;
	}
}
