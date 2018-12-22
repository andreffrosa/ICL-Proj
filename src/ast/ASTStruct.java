package ast;

import environment.Environment;
import itypes.IType;
import itypes.StructType;
import itypes.TypeException;
import ivalues.IValue;
import ivalues.Struct;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ASTStruct extends ASTNodeClass {

    private Map<Entry<String, IType>, ASTNode> declarations;

    public ASTStruct(Map<Entry<String, IType>, ASTNode> declarations) {
        this.declarations = declarations;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        Map<String, IValue> values = new HashMap<>(this.declarations.size());

        for(Entry<Entry<String, IType>, ASTNode> entry : this.declarations.entrySet()) {
            values.put(entry.getKey().getKey(), entry.getValue().eval(env));
        }

        return new Struct(values);
    }

    @Override
    public IType typecheck(Environment<IType> env) {
        Map<String, IType> types = new HashMap<>(this.declarations.size());

        for(Entry<Entry<String, IType>, ASTNode> entry : this.declarations.entrySet()) {
            IType declarationType = entry.getValue().typecheck(env);
            if(!entry.getKey().getValue().equalsType(declarationType))
                throw new TypeException("=", entry.getKey().getValue(), declarationType);

            types.put(entry.getKey().getKey(), entry.getKey().getValue());
        }

        return (super.nodeType = new StructType(types));
    }

    @Override
    public String compile(Environment<String> env) {
        // TODO define a ref class that has several fields instead of one; maybe generalize the existing newRef to take a list
        return null;
    }
}
