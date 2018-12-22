package ast;

import compiler.Compiler;
import environment.Environment;
import itypes.IType;
import itypes.StructType;
import itypes.TypeException;
import ivalues.IValue;
import ivalues.Struct;

public class ASTStructDeref extends ASTNodeClass {

    private ASTNode structId;
    private String fieldId;

    public ASTStructDeref(ASTNode structId, String fieldId) {
        this.structId = structId;
        this.fieldId = fieldId;
    }

    @Override
    public IValue eval(Environment<IValue> env) {

        Struct struct = (Struct) this.structId.eval(env);

        return (struct.getField(this.fieldId));
    }

    @Override
    public IType typecheck(Environment<IType> env) {

        IType type = this.structId.typecheck(env);

        if(!(type instanceof StructType))
            throw new TypeException("Can only access fields of Struct");

        StructType structType = (StructType) type;

        IType fieldType =  structType.getField(this.fieldId);

        if(fieldType == null)
            throw  new TypeException("Not a valid Struct field Id");

        return (super.nodeType = fieldType);
    }

    @Override
    public String compile(Environment<String> env) {

        String struct = Compiler.getStructType(this.structId.getType());

        return this.structId.compile(env) +
                "checkcast " + struct + "\n" +
                "getfield " + struct + "/" + this.fieldId + " " + Compiler.ITypeToJasminType(super.nodeType) + "\n";
    }
}
