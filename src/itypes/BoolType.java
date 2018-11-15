package itypes;

public class BoolType implements IType {

	private static IType instance = null;

	protected BoolType() {
		// do nothing
	}

	public static IType getInstance() {
		if(this.instance == null) {
			this.instance = new BoolType();
		}
		return this.instance;
	}

	public String toString() {
		return "BOOL";
	}
}
