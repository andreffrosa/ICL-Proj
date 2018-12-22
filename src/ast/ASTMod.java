package ast;

import environment.Environment;

import itypes.IType;
import itypes.IntType;
import itypes.TypeException;
import ivalues.IValue;
import ivalues.Int;

public class ASTMod extends ASTNodeClass {
	
	private ASTNode left;
	private ASTNode right;
	
	public ASTMod(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environment<IValue> env) {

		Int v1 = (Int)left.eval(env);
		Int v2 = (Int)right.eval(env);

		return Int.module(v1, v2);
	}

	@Override
	public IType typecheck(Environment<IType> env) {

		IType t1 = this.left.typecheck(env);
		IType t2 = this.right.typecheck(env);

		if(t1 instanceof IntType && t2 instanceof IntType)
			return (super.nodeType = IntType.getInstance());
		else
			throw new TypeException("%", IntType.getInstance(), IntType.getInstance(), t1, t2);
	}

    @Override
    public String compile(Environment<String> env) {

		String s1 = this.left.compile(env);
		String s2 = this.right.compile(env);

		return String.format("%s\n%s\n%s\n%s\n%s\n%s\n",
				";left % right",
				";left", s1,
				";right", s2,
				"irem"
		);
    }
}