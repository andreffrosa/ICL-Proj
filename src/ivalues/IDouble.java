package ivalues;

public class IDouble implements IValue {
	
	private double value;
	
	public IDouble(double value) {
			this.value = value;
	}
	
	public double getValue() {
		return this.value;
	}

    @Override
    public String toString() {
        //return "Double : " + this.value;
    	return String.valueOf(this.value);
    }

    // NON-STATIC METHODS
	// Value methods
    public IDouble symmetric() {
	    return new IDouble(-this.value);
    }

	public IDouble multiplyBy(IDouble i) {
		return new IDouble(this.value * i.getValue());
	}
	
	public IDouble divideBy(IDouble i) {
		return new IDouble(this.value / i.getValue());
	}
	
	public IDouble add(IDouble i) {
		return new IDouble(this.value + i.getValue());
	}
	
	public IDouble subtract(IDouble i) {
		return new IDouble(this.value - i.getValue());
	}

	// Relation methods
	public Bool equalsTo(IDouble i) {
		return new Bool(this.value == i.getValue());
	}

	public Bool differentFrom(IDouble i) {
		return new Bool(this.value != i.getValue());
	}

	public Bool lesserThan(IDouble i) {
		return new Bool(this.value < i.getValue());
	}

	public Bool greaterThan(IDouble i) {
		return new Bool(this.value > i.getValue());
	}

	public Bool lesserEqualThan(IDouble i) {
		return new Bool(this.value <= i.getValue());
	}

	public Bool greaterEqualThan(IDouble i) {
		return new Bool(this.value >= i.getValue());
	}

	// STATIC METHODS
	// Value methods
    public static IDouble symmetry(IDouble i) {
        return new IDouble(-i.getValue());
    }

	public static IDouble multiplication(IDouble d1, IDouble d2) {
		return new IDouble(d1.getValue() * d2.getValue());
	}
	
	public static IDouble division(IDouble d1, IDouble d2) {
		return new IDouble(d1.getValue() / d2.getValue());
	}
	
	public static IDouble module(IDouble d1, IDouble d2) {
		return new IDouble(d1.getValue() % d2.getValue());
	}
	
	public static IDouble addition(IDouble d1, IDouble d2) {
		return new IDouble(d1.getValue() + d2.getValue());
	}
	
	public static IDouble subtraction(IDouble d1, IDouble d2) {
		return new IDouble(d1.getValue() - d2.getValue());
	}

	// Relation methods
	public static Bool equal(IDouble d1, IDouble d2) {
		return new Bool(d1.getValue() == d2.getValue());
	}

	public static Bool different(IDouble d1, IDouble d2) {
		return new Bool(d1.getValue() != d2.getValue());
	}

	public static Bool lesser(IDouble d1, IDouble d2) {
		return new Bool(d1.getValue() < d2.getValue());
	}

	public static Bool greater(IDouble d1, IDouble d2) {
		return new Bool(d1.getValue() > d2.getValue());
	}

	public static Bool lesserEqual(IDouble d1, IDouble d2) {
		return new Bool(d1.getValue() <= d2.getValue());
	}

	public static Bool greaterEqual(IDouble d1, IDouble d2) {
		return new Bool(d1.getValue() >= d2.getValue());
	}
}