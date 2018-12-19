package ast;

import environment.Environment;
import environment.FrameEnvironment;
import itypes.IType;
import itypes.IntType;
import itypes.TypeException;
import ivalues.IValue;
import ivalues.Int;

public class ASTSub extends ASTNodeClass {
	
	private ASTNode left;
	private ASTNode right;
	
	public ASTSub(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environment<IValue> e) {
		
		IValue v1, v2;
		v1 = left.eval(e);
		v2 = right.eval(e);
		
		return new Int(((Int)v1).getValue() - ((Int)v2).getValue());
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t1 = this.left.typecheck(env);
		IType t2 = this.right.typecheck(env);

		if(t1 instanceof IntType && t2 instanceof IntType)
			return (super.nodeType = IntType.getInstance());
		else
			throw new TypeException("-", IntType.getInstance(), IntType.getInstance(), t1, t2);
	}

    @Override
    public String compile(FrameEnvironment env) {
		String s1 = this.left.compile(env);
		String s2 = this.right.compile(env);

		return String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
				";left + right",
				";left", s1,
				";right",
				s2,
				"ineg",
				"iadd"
		);
    }
}