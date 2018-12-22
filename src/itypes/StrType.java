package itypes;

public class StrType implements IType {

	private static IType instance =  new StrType();

	private StrType() {
		// do nothing
	}

	public static IType getInstance() {
		return StrType.instance;
	}

	@Override
	public String toString() {
		return "STRING";
	}

	@Override
	public boolean equalsType(IType type) {
		if(type == null)
			return false;

		return (type instanceof StrType);
	}
}
