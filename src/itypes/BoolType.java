package itypes;

public class BoolType implements IType {

	private static IType instance =  new BoolType();

	private BoolType() {
		// do nothing
	}

	public static IType getInstance() {
		return BoolType.instance;
	}

	public String toString() {
		return "BOOL";
	}
}
