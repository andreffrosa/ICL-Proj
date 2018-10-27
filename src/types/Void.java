package types;

public class Void implements IValue {

    public Void() {

    }

    @Override
    public String toString() {
        return this.getClass().getName();
    }
}
