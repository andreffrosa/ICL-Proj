package ivalues;

public class Bool implements IValue {

	private boolean truthVal;

	public Bool(boolean truthVal) {
		this.truthVal = truthVal;
	}

	public boolean getValue() {
		return this.truthVal;
	}

    @Override
    public String toString() {
        return "Bool : " + this.truthVal;
    }

    // NON-STATIC METHODS
	public Bool not() {
		return new Bool(!this.truthVal);
	}
	
	public Bool or(Bool b) {
		return new Bool(this.truthVal || b.getValue());
	}
	
	public Bool and(Bool b) {
		return new Bool(this.truthVal && b.getValue());
	}

	// STATIC METHODS
    public static Bool negation(Bool b) {
        return new Bool(!b.getValue());
	}
	
	public static Bool conjunction(Bool b1, Bool b2) {
		return new Bool(b1.getValue() && b2.getValue());
	}
	
	public static Bool disjunction(Bool b1, Bool b2) {
		return new Bool(b1.getValue() || b2.getValue());
	}
}