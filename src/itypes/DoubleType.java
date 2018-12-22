package itypes;

public class DoubleType implements IType {

	private static IType instance = new DoubleType();

	private DoubleType() {
		// do nothing
	}

	public static IType getInstance() {
		return DoubleType.instance;
	}

	public String toString() {
		return "DOUBLE";
	}

	@Override
	public boolean equalsType(IType type) {
		if(type == null)
			return false;

		return (type instanceof DoubleType);
	}
}
