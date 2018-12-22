package itypes;

import java.util.Map;

public class StructType implements IType {

    private Map<String, IType> fields;

    public StructType(Map<String, IType> fields) {
        this.fields = fields;
    }

    public IType getField(String id) {
        return this.fields.get(id);
    }

    public Map<String, IType> getFields() {
        return this.fields;
    }

    @Override
    public boolean equalsType(IType type) {
        if(type == null)
            return false;

        if(!(type instanceof StructType))
            return false;

        Map<String, IType> otherFields = ((StructType) type).getFields();

        if(otherFields.size() != this.fields.size())
            return false;

        for(String id : this.fields.keySet())
            if(!otherFields.containsKey(id))
                return false;

        return true;
    }
}
