package itypes;

public class RefType implements IType {

	private IType referredType;

	public RefType(IType referredType) {
		this.referredType = referredType;
	}

	public IType getReferredType() {
		return this.referredType;
	}

	public String toString() {
		return "REF" + this.referredType;
	}
}
