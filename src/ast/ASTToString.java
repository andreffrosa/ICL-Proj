package ast;

import environment.Environment;

import itypes.BoolType;
import itypes.DoubleType;
import itypes.FunType;
import itypes.IType;
import itypes.IntType;
import itypes.RefType;
import itypes.StrType;
import itypes.TypeException;
import ivalues.IValue;
import ivalues.Str;
import compiler.Compiler;

public class ASTToString extends ASTNodeClass {
	
	private ASTNode node;

	public ASTToString(ASTNode node) {
		this.node = node;
	}

    @Override
	public IValue eval(Environment<IValue> env) {
    	IValue v = this.node.eval(env);
    	return new Str(v.toString());
	}

	@Override
	public IType typecheck(Environment<IType> env) {
		IType t = this.node.typecheck(env);
		if( ! (t instanceof IntType || t instanceof BoolType || t instanceof RefType || t instanceof FunType || t instanceof StrType) )
			throw new TypeException("Cannot convert to String");
		
		this.nodeType = t;
		
		return StrType.getInstance();
	}

	public static String convertToString(IType type) {
		if( type instanceof StrType) 
			return "invokestatic java/lang/String/valueOf(Ljava/lang/Object;)Ljava/lang/String;\n";
		
		String convert = "     ;convert to String;\n";
		if( type instanceof IntType ) {
			return convert + "     invokestatic java/lang/String/valueOf(I)Ljava/lang/String;\n";
		} else if( type instanceof DoubleType ) {
			return convert + "     invokestatic java/lang/String/valueOf(Ljava/lang/Object;)Ljava/lang/String;\n";
		} else if( type instanceof BoolType ) {
			return "invokestatic     java/lang/String.valueOf(Z)Ljava/lang/String;\n";
		} else if( type instanceof FunType ) {
			convert += "new Ljava/lang/String;\n";
			convert += "dup\n";
			convert += "ldc " + "\"" + Compiler.computeSignature(type) + "\"\n";
			convert += "invokespecial java/lang/String/<init>(Ljava/lang/String;)V\n";
			return convert;
		} else if( type instanceof RefType ) {
			IType t = type;
			String s1 = "", s2 = "", getfield = "";
			while( t instanceof RefType ) {
				s1 += "[";
				s2 += "]";
				t = ((RefType)t).getReferencedType();
				String ref_class = Compiler.getRefType(t);
				getfield += "checkcast " + ref_class + "\n"
				          + "getfield " + ref_class + "/v " + Compiler.ITypeToJasminType(t) + "\n";
			}
			if( t instanceof StrType ) {
				s1 += "\\\"";
				s2 = "\\\"" + s2;
			}
			
			return convert + getfield
			     + convertToString(t)
			     + "astore_3\n"
			     + "new java/lang/StringBuilder\n"
			     + "dup\n"
				 + "ldc \"" + s1 + "\"\n"
				 + "invokestatic java/lang/String/valueOf(Ljava/lang/Object;)Ljava/lang/String;\n"
			     + "invokespecial java/lang/StringBuilder/<init>(Ljava/lang/String;)V\n"
				 + "aload_3\n"
				 + "invokevirtual java/lang/StringBuilder/append(Ljava/lang/String;)Ljava/lang/StringBuilder;\n"
				 + "ldc \"" + s2 + "\"\n"
				 + "invokevirtual java/lang/StringBuilder/append(Ljava/lang/String;)Ljava/lang/StringBuilder;\n"
				 + "invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;\n";
		}
		return null;
	}
	
	@Override
	public String compile(Environment<String> env) {
		return this.node.compile(env) + "\n" +
				 convertToString(this.node.getType());
	}

}