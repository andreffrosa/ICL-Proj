package ast;

import environment.Environment;
import environment.FrameEnvironment;
import itypes.IType;
import itypes.IntType;
import itypes.TypeException;
import ivalues.IValue;
import ivalues.Int;

public class ASTMult extends ASTNodeClass {
	
	private ASTNode left;
	private ASTNode right;
	
	public ASTMult(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environment<IValue> env) {

        IValue leftVal = this.left.eval(env);
        IValue rightVal = this.right.eval(env);

		return Int.multiplication((Int)leftVal, (Int)rightVal);
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t1 = this.left.typecheck(env);
		IType t2 = this.right.typecheck(env);

		if(t1 instanceof IntType && t2 instanceof IntType)
			return (super.nodeType = IntType.getInstance());
		else
			throw new TypeException("*", IntType.getInstance(), IntType.getInstance(), t1, t2);
	}

    @Override
    public String compile(FrameEnvironment env) {
		String s1 = this.left.compile(env);
		String s2 = this.right.compile(env);

		return String.format("%s\n%s\n%s\n%s\n%s\n%s\n",
				";left * right",
				";left", s1,
				";right", s2,
				"imul"
		);
    }
}