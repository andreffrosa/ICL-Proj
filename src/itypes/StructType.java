package itypes;

import java.util.Iterator;
import java.util.List;

public class StructType implements IType {

    private List<IType> fields;

    public StructType(List<IType> fields) {
        this.fields = fields;
    }

    public List<IType> getFields() {
        return this.fields;
    }

    @Override
    public boolean equalsType(IType type) {
        if(type == null)
            return false;

        if(!(type instanceof StructType))
            return false;

        List<IType> otherFields = ((StructType) type).getFields();

        if(otherFields.size() != this.fields.size())
            return false;

        Iterator<IType> otherFieldsIterator = otherFields.iterator();
        Iterator<IType> thisFieldsIterator = this.fields.iterator();

        while(otherFieldsIterator.hasNext() && thisFieldsIterator.hasNext()) {
            if(!otherFieldsIterator.next().equalsType(thisFieldsIterator.next()))
                return false;
        }

        return true;
    }
}
