package ast;

import compiler.StackCoordinates;
import environment.Environment;

import itypes.BoolType;
import itypes.FunType;
import itypes.IType;
import itypes.IntType;
import itypes.RefType;
import itypes.TypeException;
import ivalues.Bool;
import ivalues.IValue;

public class ASTPrintln implements ASTNode {
	
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
		return t;
	}

	@Override
	public String compile(Environment<StackCoordinates> env) {
		
		String s = this.node.compile(env);
		
		String printStream = "     ;PrintStream object held in java.lang.out\n" + 
				   		  "     getstatic java/lang/System/out Ljava/io/PrintStream;\n";
		
		String print = "     ; call println \n" + 
				   	   "     invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V";
		
		String convert = "     ;convert to String;\n";
		if( type instanceof IntType ) {
			convert += "     invokestatic java/lang/String/valueOf(I)Ljava/lang/String;\n";
		} else if( type instanceof BoolType ) {
			convert += "invokestatic     java/lang/String.valueOf(Z)Ljava/lang/String;\n";
		} else if( type instanceof FunType ) {
			// TODO:
		} else if( type instanceof RefType ) {
			// TODO:
		}

		String code = String.format("%s\n%s\n%s\n%s\n", 
				printStream, 
				s, 
				convert, 
				print
				);

		return code;
	}

}