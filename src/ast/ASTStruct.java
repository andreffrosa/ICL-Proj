package ast;

import compiler.Compiler;
import environment.Environment;
import itypes.IType;
import itypes.StructType;
import itypes.TypeException;
import ivalues.IValue;
import ivalues.Struct;

import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class ASTStruct extends ASTNodeClass {

    private List<Entry<Entry<String, IType>, ASTNode>> declarations;

    public ASTStruct(List<Entry<Entry<String, IType>, ASTNode>> declarations) {
        this.declarations = declarations;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        List<Entry<String, IValue>> values = new LinkedList<>();

        for(Entry<Entry<String, IType>, ASTNode> entry : this.declarations) {
            values.add(new SimpleEntry<>(entry.getKey().getKey(), entry.getValue().eval(env)));

        }

        return new Struct(values);
    }

    @Override
    public IType typecheck(Environment<IType> env) {
        List<Entry<String, IType>> types = new LinkedList<>();

        for(Entry<Entry<String, IType>, ASTNode> entry : this.declarations) {
            IType declarationType = entry.getValue().typecheck(env);
            if(!entry.getKey().getValue().equalsType(declarationType))
                throw new TypeException("=", entry.getKey().getValue(), declarationType);

            types.add(new SimpleEntry<>(entry.getKey().getKey(), declarationType));
        }

        return (super.nodeType = new StructType(types));
    }

    @Override
    public String compile(Environment<String> env) {
        String structId = Compiler.getStructType(super.nodeType);

        StringBuilder builder = new StringBuilder();
        builder.append("new ").append(structId).append("\n");
        builder.append("dup\n");
        builder.append("invokespecial ").append(structId).append("/<init>()V\n");

        for(Entry<Entry<String, IType>, ASTNode> entry: this.declarations){
            builder.append("dup\n");
            builder.append(entry.getValue().compile(env));
            builder.append("putfield ").append(structId).append("/").append(entry.getKey().getKey()).append(" ").append(Compiler.ITypeToJasminType(entry.getKey().getValue())).append("\n");
        }

        return builder.toString();
    }
}
