package ivalues;

public class Cell implements IValue {

    private IValue value;

    public Cell(IValue value) {
        this.value = value;
    }

    public IValue setValue(IValue value) {
        this.value = value;
        return this.value;
    }

    public IValue getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "Cell : " + this.hashCode() + " [" + this.value.toString() + "]";
    }
}
