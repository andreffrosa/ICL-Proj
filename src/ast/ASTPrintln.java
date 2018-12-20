package ast;

import environment.Environment;

import itypes.BoolType;
import itypes.FunType;
import itypes.IType;
import itypes.IntType;
import itypes.RefType;
import environment.FrameEnvironment;
import ivalues.IValue;

// TODO verificar tudo
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
	public IType typecheck(Environment<IType> env) { // TODO: O que retorna o print e como fazer typecheck disto?

		IType t = this.node.typecheck(env);
		
		this.nodeType = t;
		
		return t;
	}

	@Override
	public String compile(FrameEnvironment env) {
		
		String s = this.node.compile(env);
		
		String printStream = "     ;PrintStream object held in java.lang.out\n" + 
				   		  "     getstatic java/lang/System/out Ljava/io/PrintStream;\n";
		
		String print = "     ; call println \n" + 
				   	   "     invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V";
		
		String convert = "     ;convert to String;\n";
		if( this.nodeType instanceof IntType ) {
			convert += "     invokestatic java/lang/String/valueOf(I)Ljava/lang/String;\n";
		} else if( this.nodeType instanceof BoolType ) {
			convert += "invokestatic     java/lang/String.valueOf(Z)Ljava/lang/String;\n";
		} else if( this.nodeType instanceof FunType ) {
			// TODO:
		} else if( this.nodeType instanceof RefType ) {
			// TODO:
		}

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