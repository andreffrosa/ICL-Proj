package itypes;

public class BoolType implements IType {

	private static IType instance =  new BoolType();

	private BoolType() {
		// do nothing
	}

	public static IType getInstance() {
		return BoolType.instance;
	}

	@Override
	public String toString() {
		return "BOOL";
	}

	@Override
	public boolean equalsType(IType type) {
		if(type == null)
			return false;

		return (type instanceof BoolType);
	}
}
