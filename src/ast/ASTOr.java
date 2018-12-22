package ast;

import environment.Environment;
import environment.FrameEnvironment;
import itypes.BoolType;
import itypes.IType;
import itypes.TypeException;
import ivalues.Bool;
import ivalues.IValue;
import compiler.Compiler;

public class ASTOr extends ASTNodeClass {
	
	private ASTNode left;
	private ASTNode right;
	
	public ASTOr(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
	    IValue leftVal = this.left.eval(env);
	    IValue rightVal = this.right.eval(env);

		return Bool.disjunction((Bool) leftVal, (Bool) rightVal);
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t1 = this.left.typecheck(env);
		IType t2 = this.right.typecheck(env);

		if(t1 instanceof BoolType && t2 instanceof BoolType)
			return (super.nodeType = BoolType.getInstance());
		else
			throw new TypeException("||", BoolType.getInstance(), BoolType.getInstance(), t1, t2);
	}

    @Override
    public String compile(FrameEnvironment env) {

		String s1 = this.left.compile(env);
		String s2 = this.right.compile(env);

		return String.format("%s\n%s\n%s\n%s\n%s\n%s\n",
				";left or right",
				";left",
				s1,
				";right",
				s2,
				"ior"
		);
    }
    
    @Override
    public String cc(FrameEnvironment env, String tl, String fl) {
    	String newlabel = Compiler.newLabel();
    	return String.format("%s\n%s\n%s\n",
				left.cc(env, tl, newlabel),
				newlabel + ": ",
				right.cc(env, tl, fl)
				);
    }
}