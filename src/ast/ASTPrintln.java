package ast;

import environment.Environment;

import itypes.BoolType;
import itypes.FunType;
import itypes.IType;
import itypes.IntType;
import itypes.RefType;
import environment.FrameEnvironment;
import ivalues.IValue;
import compiler.Compiler;

public class ASTPrintln extends ASTNodeClass {
	
	private ASTNode node;

	public ASTPrintln(ASTNode node) {
		this.node = node;
	}

    @Override
	public IValue eval(Environment<IValue> env) {
    	IValue v = this.node.eval(env);
    	System.out.println(v.toString());
   		return v;
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t = this.node.typecheck(env);
		
		this.nodeType = t;
		
		return t;
	}

	private String convertToString(IType type) {
		if( type instanceof IntType ) {
			return "     invokestatic java/lang/String/valueOf(I)Ljava/lang/String;\n";
		} else if( type instanceof BoolType ) {
			return "invokestatic     java/lang/String.valueOf(Z)Ljava/lang/String;\n";
		} else if( type instanceof FunType ) {
			String convert = "new Ljava/lang/String;\n";
			convert += "dup\n";
			convert += "ldc " + "\"" + Compiler.computeSignature(this.nodeType) + "\"\n";
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
			return getfield
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
	public String compile(FrameEnvironment env) {
		
		String s = this.node.compile(env);
		
		String printStream = "     ;PrintStream object held in java.lang.out\n" + 
				   		     "     getstatic java/lang/System/out Ljava/io/PrintStream;\n";
		
		String print = "     ; call println \n" + 
				   	   "     invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V";
		
		String convert = "     ;convert to String;\n"
					   + "     " + convertToString(this.nodeType);
		

		String code = String.format("%s\n%s\n%s\n%s\n%s\n%s\n",
				s, 
				"dup\n",
				printStream,
				"swap",
				convert, 
				print
				);

		return code;
	}

}