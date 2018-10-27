package ivalues;

public class Int implements IValue {
	
	private int value;
	
	public Int(int value) {
			this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}

    @Override
    public String toString() {
        return "" + this.value;
    }

    // NON-STATIC METHODS
	// Value methods
    public Int symmetric() {
	    return new Int(-this.value);
    }

	public Int multiplyBy(Int i) {
		return new Int(this.value * i.getValue());
	}
	
	public Int divideBy(Int i) {
		return new Int(this.value / i.getValue());
	}
	
	public Int add(Int i) {
		return new Int(this.value + i.getValue());
	}
	
	public Int subtract(Int i) {
		return new Int(this.value - i.getValue());
	}

	// Relation methods
	public Bool equalsTo(Int i) {
		return new Bool(this.value == i.getValue());
	}

	public Bool differentFrom(Int i) {
		return new Bool(this.value != i.getValue());
	}

	public Bool lesserThan(Int i) {
		return new Bool(this.value < i.getValue());
	}

	public Bool greaterThan(Int i) {
		return new Bool(this.value > i.getValue());
	}

	public Bool lesserEqualThan(Int i) {
		return new Bool(this.value <= i.getValue());
	}

	public Bool greaterEqualThan(Int i) {
		return new Bool(this.value >= i.getValue());
	}

	// STATIC METHODS
	// Value methods
    public static Int symmetry(Int i) {
        return new Int(-i.getValue());
    }

	public static Int multiplication(Int i1, Int i2) {
		return new Int(i1.getValue() * i2.getValue());
	}
	
	public static Int division(Int i1, Int i2) {
		return new Int(i1.getValue() / i2.getValue());
	}
	
	public static Int addition(Int i1, Int i2) {
		return new Int(i1.getValue() + i2.getValue());
	}
	
	public static Int subtraction(Int i1, Int i2) {
		return new Int(i1.getValue() - i2.getValue());
	}

	// Relation methods
	public static Bool equal(Int i1, Int i2) {
		return new Bool(i1.getValue() == i2.getValue());
	}

	public static Bool different(Int i1, Int i2) {
		return new Bool(i1.getValue() != i2.getValue());
	}

	public static Bool lesser(Int i1, Int i2) {
		return new Bool(i1.getValue() < i2.getValue());
	}

	public static Bool greater(Int i1, Int i2) {
		return new Bool(i1.getValue() > i2.getValue());
	}

	public static Bool lesserEqual(Int i1, Int i2) {
		return new Bool(i1.getValue() <= i2.getValue());
	}

	public static Bool greaterEqual(Int i1, Int i2) {
		return new Bool(i1.getValue() >= i2.getValue());
	}
}