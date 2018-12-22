package ivalues;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class Struct implements IValue {

    private List<Entry<String, IValue>> fields;

    public Struct(List<Entry<String, IValue>> fields) {
        this.fields = fields;
    }

    public IValue getField(String id) {

        for(Entry<String, IValue> entry : this.fields)
            if(entry.getKey().equals(id))
                return entry.getValue();

        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");

        Iterator<Entry<String, IValue>> fieldsIterator = this.fields.iterator();

        while(fieldsIterator.hasNext()) {
            Entry<String, IValue> entry = fieldsIterator.next();
            builder.append(entry.getKey()).append(":").append(entry.getValue());

            if(fieldsIterator.hasNext())
                builder.append(", ");
        }

        builder.append("]");

        return builder.toString();
    }
}
