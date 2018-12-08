package itypes;

public class RefType implements IType {

	private IType referencedType;

	public RefType(IType referencedType) {
		this.referencedType = referencedType;
	}

	public IType getReferencedType() {
		return this.referencedType;
	}

	public String toString() {
		return "REF:" + this.referencedType;
	}

	@Override
	public boolean equalsType(IType type) {
		if(type == null)
			return false;

		if(!(type instanceof RefType))
			return false;

		return this.getReferencedType().equalsType(((RefType) type).getReferencedType());
	}
}
