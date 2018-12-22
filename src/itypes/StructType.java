package itypes;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class StructType implements IType {

    private List<Entry<String, IType>> fields;

    public StructType(List<Entry<String, IType>> fields) {
        this.fields = fields;
    }

    public IType getField(String id) {

        for(Entry<String, IType> entry : this.fields) {
            if (entry.getKey().equals(id))
                return entry.getValue();
        }

        return null;
    }

    public List<Entry<String, IType>> getFields() {
        return this.fields;
    }

    @Override
    public boolean equalsType(IType type) {
        if(type == null)
            return false;

        if(!(type instanceof StructType))
            return false;

        List<Entry<String, IType>> otherFields = ((StructType) type).getFields();

        if(otherFields.size() != this.fields.size())
            return false;

        Iterator<Entry<String, IType>> otherFieldsIt = otherFields.iterator();
        Iterator<Entry<String, IType>> thisFieldsIt = this.fields.iterator();

        while(otherFieldsIt.hasNext())
            if(!otherFieldsIt.next().getValue().equalsType(thisFieldsIt.next().getValue()))
                return false;

        return true;
    }
}
