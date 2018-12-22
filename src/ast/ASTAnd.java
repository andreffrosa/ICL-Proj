package ast;

import compiler.Compiler;
import environment.Environment;

import itypes.BoolType;
import itypes.IType;
import itypes.TypeException;
import ivalues.Bool;
import ivalues.IValue;

public class ASTAnd extends ASTNodeClass {
	
	private ASTNode left;
	private ASTNode right;

	public ASTAnd(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

    @Override
	public IValue eval(Environment<IValue> env) {

    	IValue v1 = this.left.eval(env);
    	IValue v2 = this.right.eval(env);
    	
   		return Bool.conjunction((Bool)v1, (Bool)v2);

	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t1 = this.left.typecheck(env);
		IType t2 = this.right.typecheck(env);

		if(t1 instanceof BoolType && t2 instanceof BoolType)
			return (super.nodeType = BoolType.getInstance());
		else
			throw new TypeException("&&", BoolType.getInstance(), BoolType.getInstance(), t1, t2);
	}

	@Override
	public String compile(Environment<String> env) {
		String s1 = this.left.compile(env);
		String s2 = this.right.compile(env);

		return String.format("%s\n%s\n%s\n%s\n%s\n%s\n",
				";left && right",
				";left",
				s1,
				";right",
				s2,
				"iand"
		);
	}
	
	@Override
    public String cc(Environment<String> env, String tl, String fl) {
    	String newlabel = Compiler.newLabel();
    	return String.format("%s\n%s\n%s\n",
				left.cc(env, newlabel, fl),
				newlabel + ": ",
				right.cc(env, tl, fl)
				);
    }

}