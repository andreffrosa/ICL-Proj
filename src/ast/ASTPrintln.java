package ast;

import environment.Environment;

import itypes.IType;
import ivalues.IValue;

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
		return (this.nodeType = this.node.typecheck(env));
	}

	@Override
	public String compile(Environment<String> env) {
		
		String s = this.node.compile(env);
		
		String printStream = "     ;PrintStream object held in java.lang.out\n" + 
				   		     "     getstatic java/lang/System/out Ljava/io/PrintStream;\n";
		
		String print = "     ; call println \n" + 
				   	   "     invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V";

		String code = String.format("%s\n%s\n%s\n%s\n%s\n%s\n",
				s, 
				"dup\n",
				printStream,
				"swap",
				ASTToString.convertToString(this.nodeType),
				print
				);

		return code;
	}

}