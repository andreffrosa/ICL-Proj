package ivalues;

public class Str implements IValue {

	private String v;

	public Str(String v) {
		this.v = v;
	}

	public String getValue() {
		return this.v;
	}

    @Override
    public String toString() {
        //return "String : " + "\"" + this.v + "\"";
    	return String.format("\"%s\"", this.v);
    }

    // NON-STATIC METHODS
	public Int length() {
		return new Int(this.v.length());
	}

	// STATIC METHODS
    public static Str concatenate(Str left, Str right) {
        return new Str(left.getValue() + right.getValue());
	}
	
}