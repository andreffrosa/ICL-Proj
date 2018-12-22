package ivalues;

import java.util.Map;

public class Struct implements IValue {

    private Map<String, IValue> fields;

    public Struct(Map<String, IValue> fields) {
        this.fields = fields;
    }

    public IValue getField(String id) {
        return this.fields.get(id);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[|");

        for (Map.Entry<String, IValue> entry : this.fields.entrySet()) {
            builder.append(entry.getKey()).append(":").append(entry.getValue()).append("|");
        }

        builder.append("]");

        return builder.toString();
    }
}
