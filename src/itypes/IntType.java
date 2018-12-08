package itypes;

public class IntType implements IType {

	private static IType instance = new IntType();

	private IntType() {
		// do nothing
	}

	public static IType getInstance() {
		return IntType.instance;
	}

	public String toString() {
		return "INT";
	}

	@Override
	public boolean equalsType(IType type) {
		if(type == null)
			return false;

		return (type instanceof IntType);
	}
}
