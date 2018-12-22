package ast;

import itypes.IType;
import ivalues.IValue;
import compiler.Compiler;
import environment.Environment;

public class ASTSeq extends ASTNodeClass {

    private ASTNode left;
    private ASTNode right;

    public ASTSeq(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        this.left.eval(env);
        return this.right.eval(env);
    }

    @Override
    public IType typecheck(Environment<IType> env) {
        this.left.typecheck(env);
        return (super.nodeType = this.right.typecheck(env));
    }

    @Override
    public String compile(Environment<String> env) {
    	
        return String.format("%s\n%s\n%s\n",
        		left.compile(env),
        		"pop",
        		right.compile(env)
		);
    }
}
