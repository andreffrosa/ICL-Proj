package ast;

import environment.Environment;
import itypes.IType;
import itypes.StructType;
import ivalues.IValue;
import ivalues.Struct;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ASTStruct extends ASTNodeClass {

    private Map<Entry<String, IType>, ASTNode> declarations;

    public ASTStruct(Map<Entry<String, IType>, ASTNode> declarations) {
        this.declarations = declarations;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        List<IValue> values = new LinkedList<>();

        for(Entry<Entry<String, IType>, ASTNode> entry : this.declarations.entrySet()) {
            values.add(entry.getValue().eval(env));
        }

        return new Struct(values);
    }

    @Override
    public IType typecheck(Environment<IType> env) {
        List<IType> types = new LinkedList<>();

        for(Entry<Entry<String, IType>, ASTNode> entry : this.declarations.entrySet()) {
            types.add(entry.getValue().typecheck(env));
        }

        return (super.nodeType = new StructType(types));
    }

    @Override
    public String compile(Environment<String> env) {
        // TODO define a ref class that has several fields instead of one; maybe generalize the existing newRef to take a list
        return null;
    }
}
