package itypes;

public class IntType implements IType {

	private static IType instance = null;

	protected IntType() {
		// do nothing
	}

	public static IType getInstance() {
		if(this.instance == null) {
			this.instance = new IntType();
		}
		return this.instance;
	}

	public String toString() {
		return "INT";
	}
}
