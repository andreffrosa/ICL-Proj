package ivalues;

import java.util.Iterator;
import java.util.List;

public class Struct implements IValue {

    private List<IValue> fields;

    public Struct(List<IValue> fields) {
        this.fields = fields;
    }

    public List<IValue> getFields() {
        return this.fields;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Iterator<IValue> fieldsIterator = this.fields.iterator();

        while(fieldsIterator.hasNext()) {
            builder.append(fieldsIterator.next());
            if(fieldsIterator.hasNext())
                builder.append(":");
        }
        builder.append("]");

        return builder.toString();
    }
}
